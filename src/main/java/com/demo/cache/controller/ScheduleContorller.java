package com.demo.cache.controller;

import com.demo.cache.schedule.TestSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

/**
 * Created on 2019/1/10
 *
 * @author dean
 * @email loveangelo0217@gmail.com
 * @since 1.0
 */
@RestController
public class ScheduleContorller {
    @Autowired
    ThreadPoolTaskScheduler threadPoolTaskScheduler;

    private Map<String,ScheduledFuture<?>> futures = new HashMap<>();

    @GetMapping("testS/{name}")
    public String testSchedule(@PathVariable String name){
        TestSchedule testSchedule = new TestSchedule();
        testSchedule.setName(name);
        if (futures.get(name) != null) {
            futures.get(name).cancel(true);
        }else{
            System.out.println("is null");
        }
        futures.put(name, threadPoolTaskScheduler.schedule(testSchedule, new CronTrigger("0/10 * * * * *")));

        return "ok";
    }
    @GetMapping("testT/{name}")
    public String testScheduleByTime(@PathVariable String name){
        TestSchedule testSchedule = new TestSchedule();
        testSchedule.setName(name);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, 5);
        futures.put(name, threadPoolTaskScheduler.schedule(testSchedule, calendar.getTime()));

        return "ok";
    }
    @GetMapping("stop/{name}")
    public String stop(@PathVariable String name){
        System.out.println(futures.get(name).isDone());
        System.out.println(futures.get(name).isCancelled());
        if (futures.get(name) != null) {
            futures.get(name).cancel(true);
        }
        return "ok";
    }
}
