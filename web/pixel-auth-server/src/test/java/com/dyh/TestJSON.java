package com.dyh;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dyh.entity.po.PUser;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {PixelAuthServerApplication.class})
public class TestJSON {

    @Test
    public void json2ObjectTest() {
        //String userStr = "{\"age\":18,\"name\":\"张三\",\"sex\":\"男\"}";
        //User user=new User(18,"张三","男");
        //User user=new User();
        //user.setAge(18);
        //user.setName("张三");
        //user.setSex("男");
        //String userStr= JSON.toJSONString(user);
        //User user1;

        PUser user=new PUser();
        user.setPassword("hello");
        user.setUsername("张三");
        String userStr= JSON.toJSONString(user);
        PUser user1;

        //JSON字符串转换成Java对象
        user1 = JSONObject.parseObject(userStr, PUser.class);
        System.out.println("JSON字符串转换成Java对象\n" + user1);//user {name='张三', sex='男', age=18}
    }
}