package com.demo.cache.cache;

import com.demo.cache.dao.UserInfoMapper;
import com.demo.cache.vo.domain.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * Created on 2019/1/9
 *
 * @author dean
 * @email loveangelo0217@gmail.com
 * @since 1.0
 */
@Component
public class UserCache {
    @Autowired
    UserInfoMapper userInfoMapper;

    @Cacheable(cacheNames = "userInfo" , key = "#userSeq", cacheManager = "testManger")
    public Object getUser(int userSeq){
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userSeq);
        System.out.println(">>>>>>>>>>in");
        return userInfo;
    }

    @Cacheable(cacheNames = "key2" , key = "#userSeq", cacheManager = "test2Manager")
    public UserInfo getkey2(int userSeq){
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userSeq);
        System.out.println(">>>>>>>>>>in2");
        return userInfo;
    }
}
