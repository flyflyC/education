package com.flyedu.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.flyedu.entity.Permission;
import com.flyedu.entity.RolePermission;
import com.flyedu.entity.User;
import com.flyedu.exceptionhandler.EduException;
import com.flyedu.mapper.PermissionMapper;
import com.flyedu.service.PermissionService;
import com.flyedu.helper.MemuHelper;
import com.flyedu.helper.PermissionHelper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.flyedu.service.RolePermissionService;
import com.flyedu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 权限 服务实现类
 * </p>
 *
 * @author cai fei fei
 * @since 2020-10-21
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Autowired
    private RolePermissionService rolePermissionService;

    @Autowired
    private UserService userService;
    /**
     * 查询所有菜单
     * @return
     */
    @Override
    public List<Permission> queryAllMenu() {
        //查询菜单表所有数据
        QueryWrapper<Permission> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        List<Permission> permissions = baseMapper.selectList(wrapper);

        //查询所有菜单集合，按照要求进行封装
        List<Permission> result = bulidPermission(permissions);

        return result;
    }

    @Override
    public void removeAllChildren(String id) {
        //创建一个集合封装所有要删除的菜单id
        List<String> isId = new ArrayList<>();

        this.selectChildernId(id,isId);
        int i = baseMapper.deleteBatchIds(isId);
        if (i<0){
            throw new EduException(20001,"删除失败！");
        }
    }

    /**
     * 给角色分配权限
     * @param roleId 角色id
     * @param permissionId 菜单id：数组形式
     */
    @Override
    public void saveRolePermissionRealtionShip(String roleId, String[] permissionId) {
        //创建集合用于封装要添加的数据
        List<RolePermission> rolePermissionList = new ArrayList<>();
        //遍历所有菜单数组
        for (String pid:permissionId){
            //将id封装到集合
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRoleId(roleId);
            rolePermission.setPermissionId(pid);
            rolePermissionList.add(rolePermission);
        }
        //添加到数据库
        rolePermissionService.saveBatch(rolePermissionList);
    }

    @Override
    public List<String> selectPermissionValueByUserId(String id) {
        List<String> selectPermissionValueList = null;
        if(this.isSysAdmin(id)) {
            //如果是系统管理员，获取所有权限
            selectPermissionValueList = baseMapper.selectAllPermissionValue();
        } else {
            selectPermissionValueList = baseMapper.selectPermissionValueByUserId(id);
        }
        return selectPermissionValueList;
    }

    @Override
    public List<JSONObject> selectPermissionByUserId(String userId) {
        List<Permission> selectPermissionList = null;
        if(this.isSysAdmin(userId)) {
            //如果是超级管理员，获取所有菜单
            selectPermissionList = baseMapper.selectList(null);
        } else {
            selectPermissionList = baseMapper.selectPermissionByUserId(userId);
        }

        List<Permission> permissionList = PermissionHelper.bulid(selectPermissionList);
        List<JSONObject> result = MemuHelper.bulid(permissionList);
        return result;
    }

    /**
     * 根据角色获取菜单
     * @param roleId
     * @return
     */
    @Override
    public List<Permission> selectAllMenu(String roleId) {
        List<Permission> allPermissionList = baseMapper.selectList(new QueryWrapper<Permission>().orderByAsc("CAST(id AS SIGNED)"));

        //根据角色id获取角色权限
        List<RolePermission> rolePermissionList = rolePermissionService.list(new QueryWrapper<RolePermission>().eq("role_id",roleId));
        //转换给角色id与角色权限对应Map对象
//        List<String> permissionIdList = rolePermissionList.stream().map(e -> e.getPermissionId()).collect(Collectors.toList());
//        allPermissionList.forEach(permission -> {
//            if(permissionIdList.contains(permission.getId())) {
//                permission.setSelect(true);
//            } else {
//                permission.setSelect(false);
//            }
//        });
        for (int i = 0; i < allPermissionList.size(); i++) {
            Permission permission = allPermissionList.get(i);
            for (int m = 0; m < rolePermissionList.size(); m++) {
                RolePermission rolePermission = rolePermissionList.get(m);
                if(rolePermission.getPermissionId().equals(permission.getId())) {
                    permission.setSelect(true);
                }
            }
        }


        List<Permission> permissionList = bulid(allPermissionList);
        return permissionList;
    }

    /**
     * 判断用户是否系统管理员
     * @param userId
     * @return
     */
    private boolean isSysAdmin(String userId) {
        User user = userService.getById(userId);

        if(null != user && "admin".equals(user.getUsername())) {
            return true;
        }
        return false;
    }

    /**
     * 根据当前id查询其他菜单的pid是否等于当前id,然后封装
     * @param id
     * @param isId
     */
    private void selectChildernId(String id, List<String> isId) {
        QueryWrapper<Permission> wrapper =new QueryWrapper<>();
        wrapper.eq("pid",id);
        wrapper.select("id");
        List<Permission> childernId = baseMapper.selectList(wrapper);

        //把childernId中的ID取出放到isId中，然后在做递归
        childernId.stream().forEach(item->{
            //封装到isId中
            isId.add(item.getId());
            //递归查询
            this.selectChildernId(item.getId(),isId);
        });
    }

    /**
     * 使用递归方法建菜单
     * @param treeNodes
     * @return
     */
    private static List<Permission> bulid(List<Permission> treeNodes) {
        List<Permission> trees = new ArrayList<>();
        for (Permission treeNode : treeNodes) {
            if ("0".equals(treeNode.getPid())) {
                treeNode.setLevel(1);
                trees.add(findChildren(treeNode,treeNodes));
            }
        }
        return trees;
    }
    /**
     * 递归查找子节点
     * @param treeNodes
     * @return
     */
    private static Permission findChildren(Permission treeNode,List<Permission> treeNodes) {
        treeNode.setChildren(new ArrayList<Permission>());

        for (Permission it : treeNodes) {
            if(treeNode.getId().equals(it.getPid())) {
                int level = treeNode.getLevel() + 1;
                it.setLevel(level);
                if (treeNode.getChildren() == null) {
                    treeNode.setChildren(new ArrayList<>());
                }
                treeNode.getChildren().add(findChildren(it,treeNodes));
            }
        }
        return treeNode;
    }
    /**
     * 把所有菜单集合返回并，进行一级二级三级进行封装
     * @param permissions
     * @return
     */
    private static List<Permission> bulidPermission(List<Permission> permissions) {
        List<Permission> resultNode = new ArrayList<>();

        //遍历数组，获取一级菜单，并设置他是level=1
        for (Permission permissionNode : permissions){
            //得到顶层菜单，设置层级为1
            if("0".equals(permissionNode.getPid())){
                permissionNode.setLevel(1);
                //根据顶层菜单，向里面查询子菜单，封装到resultNode
                resultNode.add(selectChildren(permissionNode,permissions));
            }
        }
        return resultNode;
    }

    private static Permission selectChildren(Permission permissionNode, List<Permission> permissions) {
        //向一层菜单放二层，二层放三层...初始化
        permissionNode.setChildren(new ArrayList<>());
        //遍历所有list集合，根据二级菜单的pid为一级菜单的id进行判断
        for (Permission it : permissions){
            if (permissionNode.getId().equals(it.getPid())){
                int level = permissionNode.getLevel()+1;
                it.setLevel(level);
                if (permissionNode.getChildren()==null){
                    permissionNode.setChildren(new ArrayList<>());
                }
                //将查询出的子菜单放入父菜单
                permissionNode.getChildren().add(selectChildren(it,permissions));
            }
        }
        return permissionNode;
    }
}
