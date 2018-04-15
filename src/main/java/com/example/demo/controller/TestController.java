package com.example.demo.controller;

import com.example.demo.util.JsonUtils;
import com.example.demo.util.RedisClient;
import com.example.demo.util.RedisKeys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * Created by liulanhua on 2018/2/6.
 */
@RestController
public class TestController {

    @Autowired
    private RedisClient redisClient;


    @RequestMapping("/uid")
    public String uid(HttpSession session) {
        UUID uid = (UUID) session.getAttribute("uid");
        if (uid == null) {
            uid = UUID.randomUUID();
        }
        session.setAttribute("uid", uid);

        redisClient.set(RedisKeys.REDIS_PRE_KEY+"_uuid", JsonUtils.object2Json(uid));
        System.out.println("================ redisClient.get =====================");
        System.out.println(redisClient.get(RedisKeys.REDIS_PRE_KEY+"_uuid"));

        return session.getId();
    }


}
