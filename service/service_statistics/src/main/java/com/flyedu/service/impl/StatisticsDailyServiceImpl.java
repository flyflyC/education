package com.flyedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.flyedu.client.UcentenClient;
import com.flyedu.entity.StatisticsDaily;
import com.flyedu.mapper.StatisticsDailyMapper;
import com.flyedu.service.StatisticsDailyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    /**
     * 显示需要展示的类型和时间区段
     * @param type
     * @param begin
     * @param end
     * @return
     */
    @Override
    public Map<String, Object> getShowDate(String type, String begin, String end) {
        QueryWrapper<StatisticsDaily> wrapper = new QueryWrapper<>();
        //between在两者之间
        wrapper.between("date_calculated",begin,end);
        //需要查询的字段名字
        wrapper.select("date_calculated",type);

        List<StatisticsDaily> statisticsDailies = baseMapper.selectList(wrapper);
        List<String> dateCalculatedList = new ArrayList<>();
        List<Integer> numList = new ArrayList<>();
        for (StatisticsDaily sta:statisticsDailies) {
            dateCalculatedList.add(sta.getDateCalculated());
            //封装对应数据
            switch (type){
                case "login_num":
                    numList.add( sta.getLoginNum());
                    break;
                case "register_num":
                    numList.add(sta.getRegisterNum());
                    break;
                case "video_view_num":
                    numList.add(sta.getVideoViewNum());
                    break;
                case "course_num":
                    numList.add(sta.getCourseNum());
                    break;
                default:
                    break;
            }
        }
        //把封装之后两个list集合放到map集合，进行返回
        Map<String, Object> map = new HashMap<>();
        map.put("date_calculatedList",dateCalculatedList);
        map.put("numDataList",numList);
        return map;
    }
}
