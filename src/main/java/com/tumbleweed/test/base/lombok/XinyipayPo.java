package com.tumbleweed.test.base.lombok;

import lombok.*;

import java.io.Serializable;

/**
 * Created by mylover on 7/27/16.
 */
@Setter
@Getter
@NoArgsConstructor // 空参构造
@AllArgsConstructor //全参构造
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
@Data//@Setter+@Getter+@EqualsAndHashCode+@NoArgsConstructor
@Builder
public class XinyipayPo implements Serializable {

    private String requestId;
    private String appId;
    private String accountDate;
    private String currency;
    private double tradeAmount;
    private double privilegePrice;
    private double xinyiShell;
    private double cash;
    private String payChannel;

    @NonNull//配合 RequiredArgsConstructor 生成非空校验，空位空指针异常
    private String type;// 1 收款 2 退款

}
