package com.demo.cache.config;

import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created on 2019/1/9
 *
 * @author dean
 * @email loveangelo0217@gmail.com
 * @since 1.0
 */
@Configuration
@EnableCaching
@EnableScheduling
public class CachingConfig {
    @Bean("testManger")
    @Primary
    public CacheManager cacheManager(){
        Caffeine caffeine = Caffeine.newBuilder()
                .initialCapacity(100)
                .maximumSize(1000)
//                .refreshAfterWrite(5,TimeUnit.SECONDS)
                .expireAfterWrite(5, TimeUnit.SECONDS);

        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setAllowNullValues(true);
        cacheManager.setCaffeine(caffeine);
        cacheManager.setCacheLoader(cacheLoader());
        cacheManager.setCacheNames(getNames());
        return cacheManager;
    }
    @Bean("test2Manager")
    public CacheManager test2Manager(){
        Caffeine caffeine = Caffeine.newBuilder()
                .initialCapacity(100)
                .maximumSize(1000);

        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setAllowNullValues(true);
        cacheManager.setCaffeine(caffeine);
        cacheManager.setCacheLoader(cacheLoader());
        cacheManager.setCacheNames(getNames());
        return cacheManager;
    }
    /**
     * 必须要指定这个Bean，refreshAfterWrite=5s这个配置属性才生效
     *
     * @return
     */
    @Bean
    public CacheLoader<Object, Object> cacheLoader() {

        CacheLoader<Object, Object> cacheLoader = new CacheLoader<Object, Object>() {

            @Override
            public Object load(Object key) throws Exception {
                return null;
            }

//            重寫這個方法將oldValue值返回回去，進而刷新緩存
            @Override
            public Object reload(Object key, Object oldValue) throws Exception {
                return oldValue;
            }
        };

        return cacheLoader;
    }

    private static List<String> getNames(){
        List<String> names = new ArrayList<>(2);
        names.add("key2");
        names.add("userInfo");
        return names;
    }

    @CacheEvict(allEntries = true, value = {"key2", "userInfo"}, cacheManager = "test2Manager")
    @Scheduled(cron = "0 0/1 * * * ?")
    public void reportCacheEvict() {
        System.out.println("==================刷");
    }
}