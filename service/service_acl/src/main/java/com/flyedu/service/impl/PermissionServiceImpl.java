package com.flyedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.flyedu.entity.Permission;
import com.flyedu.exceptionhandler.EduException;
import com.flyedu.mapper.PermissionMapper;
import com.flyedu.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
     * 根据当前id查询其他菜单的pid是否等于当前id
     * @param id
     * @param isId
     */
    private void selectChildernId(String id, List<String> isId) {

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
