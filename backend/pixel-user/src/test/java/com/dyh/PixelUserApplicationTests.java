package com.dyh;

import com.dyh.utils.RedisIdWorker;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.dyh.constant.CommentConstants.COMMENT_PREFIX;
import static com.dyh.constant.UserConstants.USERNAME_PREFIX;

@SpringBootTest
public class PixelUserApplicationTests {

    @Resource
    RedisIdWorker redisIdWorker;

    @Test
    void testTime() throws ParseException {
        Date date = new Date();
        System.out.println(date.getTime());
        String strDateFormat = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
        System.out.println(sdf.format(date));
        int days = (int) ((new Date().getTime() - sdf.parse(sdf.format(date)).getTime()))/(1000*3600*24);
        System.out.println(days);
        int days1 = (int) ((new Date().getTime() - sdf.parse("2022-11-22").getTime()))/(1000*3600*24);
        System.out.println(days1);
    }

    @Test
    void testRedisId(){
        long nextId = redisIdWorker.nextId(COMMENT_PREFIX);
        System.out.println(nextId);
    }
}
