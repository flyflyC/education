package com.flyedu.service;

import com.flyedu.entity.StatisticsDaily;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

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

    /**
     * 显示需要展示的类型和时间区段
     * @param type
     * @param begin
     * @param end
     * @return
     */
    Map<String, Object> getShowDate(String type, String begin, String end);
}
