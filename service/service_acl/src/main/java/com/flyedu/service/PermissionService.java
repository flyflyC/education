package com.flyedu.service;

import com.alibaba.fastjson.JSONObject;
import com.flyedu.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 权限 服务类
 * </p>
 *
 * @author cai fei fei
 * @since 2020-10-21
 */
public interface PermissionService extends IService<Permission> {

    /**
     * 查询所有菜单
     * @return
     */
    List<Permission> queryAllMenu();

    /**
     * 删除所有菜单
     * @param id
     */
    void removeAllChildren(String id);

    /**
     * 给角色分配权限
     * @param roleId
     * @param permissionId
     */
    void saveRolePermissionRealtionShip(String roleId, String[] permissionId);

    /**
     * 通过用户ID查询用户所有菜单
     * @param id
     * @return
     */
    List<String> selectPermissionValueByUserId(String id);

    /**
     * 根据用户id获取用户菜单权限
     * @param id
     * @return
     */
    List<JSONObject> selectPermissionByUserId(String id);
    /**
     * 根据角色获取菜单
     * @param roleId
     * @return
     */
    List<Permission> selectAllMenu(String roleId);


}
