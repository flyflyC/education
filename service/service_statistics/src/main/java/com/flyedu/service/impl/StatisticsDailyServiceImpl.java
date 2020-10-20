package com.flyedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.flyedu.client.UcentenClient;
import com.flyedu.common.Result;
import com.flyedu.entity.StatisticsDaily;
import com.flyedu.mapper.StatisticsDailyMapper;
import com.flyedu.service.StatisticsDailyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author cai fei fei
 * @since 2020-10-20
 */
@Service
public class StatisticsDailyServiceImpl extends ServiceImpl<StatisticsDailyMapper, StatisticsDaily> implements StatisticsDailyService {

    @Autowired
    private UcentenClient ucentenClient;
    @Override
    public Integer countRegister(String day) {
        System.out.println(day);
        System.out.println("================");
        //如果有相同数据，先删除再添加
        QueryWrapper<StatisticsDaily> wrapper = new QueryWrapper<>();
        wrapper.eq("date_calculated",day);
        baseMapper.delete(wrapper);

        int countRegister = ucentenClient.countRegister(day);;
        System.out.println(countRegister);
        //添加数据到数据库
        StatisticsDaily sta = new StatisticsDaily();
        sta.setRegisterNum(countRegister);
        sta.setDateCalculated(day);

        sta.setVideoViewNum(RandomUtils.nextInt(100,200));
        sta.setLoginNum(RandomUtils.nextInt(100,200));
        sta.setCourseNum(RandomUtils.nextInt(100,200));
        int insert = baseMapper.insert(sta);
        System.out.println(insert);
        return countRegister;
    }
}
