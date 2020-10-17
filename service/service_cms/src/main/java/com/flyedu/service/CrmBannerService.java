package com.flyedu.service;

import com.flyedu.entity.CrmBanner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author cai fei fei
 * @since 2020-10-16
 */
public interface CrmBannerService extends IService<CrmBanner> {

    /**
     * 查询banner并显示前4条数据
     * @return
     */
    List<CrmBanner> seletBannerList();
}
