package com.demo.cache.controller;

import com.demo.cache.cache.UserCache;
import com.demo.cache.vo.domain.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 2019/1/9
 *
 * @author dean
 * @email loveangelo0217@gmail.com
 * @since 1.0
 */
@RestController
public class TestController {
    @Autowired
    UserCache userCache;

    @RequestMapping("/getUser")
    public Object getUser() {

        return userCache.getUser(1);
    }

    @RequestMapping("/getUser2")
    public UserInfo getUser2() {

        return userCache.getkey2(1);
    }

}
