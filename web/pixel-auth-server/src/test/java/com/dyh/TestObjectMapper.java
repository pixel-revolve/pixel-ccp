package com.dyh;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dyh.entity.po.PUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest(classes = {PixelAuthServerApplication.class})
public class TestObjectMapper {

    @Resource
    ObjectMapper objectMapper;

    @Test
    public void testObjectMapperWriteValue() throws JsonProcessingException {
        Map<String,String> map=new HashMap<>();
        map.put("hello","world");
        String s = objectMapper.writeValueAsString(map);
        System.out.println(s);
    }


    @Test
    public void testObjectMapperWriteValueNull() throws JsonProcessingException {
        Map<String,String> map=new HashMap<>();
        Object obj=map;
        String s = objectMapper.writeValueAsString(obj);
        System.out.println(s);
    }

    @Test
    public void testObjectMapperReadValue() throws JsonProcessingException {
        String jsonUser="{\"id\":1594227746362376194,\"nickname\":\"user_8cjcw6iaqi\",\"username\":\"2v93a73dk6\",\"phone\":\"13770511673\",\"email\":\"\",\"password\":\"\",\"salt\":\"\",\"status\":1,\"avatar\":\"\",\"balance\":0,\"isAdmin\":0,\"createdOn\":\"2022-11-20T07:14:48.000+00:00\",\"modifiedOn\":\"2022-11-20T07:14:48.000+00:00\",\"deletedOn\":null,\"isDel\":0}";
        PUser user= objectMapper.readValue(jsonUser,PUser.class);

        System.out.println(user.toString());
    }

}
