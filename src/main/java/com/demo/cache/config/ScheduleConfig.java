package com.demo.cache.config;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

/**
 * Created on 2019/1/10
 *
 * @author dean
 * @email loveangelo0217@gmail.com
 * @since 1.0
 */
@Component
@EnableScheduling
public class ScheduleConfig {
    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        ThreadPoolTaskScheduler  threadPoolTaskScheduler=new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(30);
        threadPoolTaskScheduler.setWaitForTasksToCompleteOnShutdown(true);
        threadPoolTaskScheduler.setRemoveOnCancelPolicy(true);
        threadPoolTaskScheduler.setThreadNamePrefix("scheduler-");
        threadPoolTaskScheduler.setErrorHandler(t -> System.out.println("error : "+t));
        threadPoolTaskScheduler.initialize();
        return threadPoolTaskScheduler;
    }
}
