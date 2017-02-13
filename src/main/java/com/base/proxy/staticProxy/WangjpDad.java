package com.base.proxy.staticProxy;

/**
 * 描述:爹
 *
 * @author: mylover
 * @Time: 13/02/2017.
 */
public class WangjpDad implements People {

    Wangjp wangjp;

    public WangjpDad(Wangjp wangjp) {
        this.wangjp = wangjp;
    }

    @Override
    public void zhaoduixiang() {

        before();

        wangjp.zhaoduixiang();

        after();

    }

    private void before() {
        System.out.println("老子先满意:得体,门当户对...");
    }

    private void after() {
        System.out.println("姑娘没问题了");
    }
}
