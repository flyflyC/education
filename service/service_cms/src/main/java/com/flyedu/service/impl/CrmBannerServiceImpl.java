package com.flyedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.flyedu.entity.CrmBanner;
import com.flyedu.mapper.CrmBannerMapper;
import com.flyedu.service.CrmBannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author cai fei fei
 * @since 2020-10-16
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {

    /**
     * 查询banner并 根据id降序排序，显示前4条数据
     * @return
     */
    @Cacheable(key = "'selectIndexList'",value = "banner")
    @Override
    public List<CrmBanner> seletBannerList() {
        QueryWrapper<CrmBanner> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        //last拼接sql语句
        wrapper.last("limit 4");
        List<CrmBanner> banners = baseMapper.selectList(wrapper);
        return banners;
    }
}
