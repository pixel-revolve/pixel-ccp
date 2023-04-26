package com.dyh.entity.vo;

import lombok.Data;

/**
 * 运费统计
 * @author pixel-revolve
 * @date 2023/04/12
 */
@Data
public class PFareVo {

    private PUserAddressVo address;

    private Long fare;
}
