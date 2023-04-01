package com.dyh;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PixelWalletApplicationTests {

    @Test
    void contextLoads() {
        String total_amount="1.00";
        double total_amount_double = Double.parseDouble(total_amount);
        Long aLong = Math.round(total_amount_double);
        System.out.println(aLong);
    }

}
