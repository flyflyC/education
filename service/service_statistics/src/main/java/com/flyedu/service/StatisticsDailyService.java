package com.flyedu.service;

import com.flyedu.entity.StatisticsDaily;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author cai fei fei
 * @since 2020-10-20
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {

    /**
     * 统计一天中的注册人数
     * @param day
     * @return
     */
    Integer countRegister(String day);
}
