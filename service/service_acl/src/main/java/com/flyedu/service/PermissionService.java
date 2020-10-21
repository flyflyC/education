package com.flyedu.service;

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
}
