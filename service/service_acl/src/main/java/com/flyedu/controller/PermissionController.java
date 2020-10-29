package com.flyedu.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.flyedu.common.Result;
import com.flyedu.entity.Permission;
import com.flyedu.service.PermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 权限 前端控制器
 * </p>
 *
 * @author cai fei fei
 * @since 2020-10-21
 */
@Api(description = "菜单管理")
@RestController
@RequestMapping("/admin/acl/permission")
@CrossOrigin
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @ApiOperation(value = "查询所有菜单")
    @GetMapping("/indexAllPermission")
    public Result indexAllPermission() {
        List<Permission> list =  permissionService.queryAllMenu();
        Map<String,Object> map =new HashMap<>();
        map.put("children",list);
        return Result.ok().data(map);
    }

    @ApiOperation(value = "删除所有菜单")
    @GetMapping("/removeAllChildren/{id}")
    public Result removeAllChildren(@PathVariable String id) {
        permissionService.removeAllChildren(id);
        return Result.ok();
    }

    @ApiOperation(value = "给角色分配权限")
    @PostMapping("/doAssign")
    public Result doAssign(String roleId, String[] permissionId) {
        permissionService.saveRolePermissionRealtionShip(roleId,permissionId);
        return Result.ok();
    }

    @ApiOperation(value = "根据角色获取菜单")
    @GetMapping("toAssign/{roleId}")
    public Result toAssign(@PathVariable String roleId) {
        List<Permission> list = permissionService.selectAllMenu(roleId);
        return Result.ok().data("children", list);
    }



    @ApiOperation(value = "新增菜单")
    @PostMapping("save")
    public Result save(@RequestBody Permission permission) {
        permissionService.save(permission);
        return Result.ok();
    }

    @ApiOperation(value = "修改菜单")
    @PutMapping("update")
    public Result updateById(@RequestBody Permission permission) {
        permissionService.updateById(permission);
        return Result.ok();
    }
}

