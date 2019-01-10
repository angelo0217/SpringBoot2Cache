package com.demo.cache.schedule;

import java.util.Date;

/**
 * Created on 2019/1/10
 *
 * @author dean
 * @email loveangelo0217@gmail.com
 * @since 1.0
 */
public class TestSchedule implements Runnable {
    private String name;

    @Override
    public void run() throws RuntimeException{
        System.out.println(new Date() + "System run name :" + name);
        if(name.equals("t2")){
            throw new RuntimeException("test error");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
