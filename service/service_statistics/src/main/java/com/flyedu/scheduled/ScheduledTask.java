package com.flyedu.scheduled;

import com.flyedu.service.StatisticsDailyService;
import com.flyedu.utils.DateUtil;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Description 定时任务
 * @ClassName scheduledTask
 * @Author cai feifei
 * @date 2020.10.21 08:35
 * @Version
 */
@Component
public class ScheduledTask {

    private StatisticsDailyService statisticsDailyService;
    @Scheduled(cron = "0 0 0,7,17 * * ?")
    public void Task(){
        statisticsDailyService.countRegister(DateUtil.formatDate(DateUtil.addDays(new Date(), -1)));
    }
}
