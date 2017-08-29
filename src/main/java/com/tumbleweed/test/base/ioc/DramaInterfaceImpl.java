package com.tumbleweed.test.base.ioc;

/**
 * 描述:接口注入
 *
 * @author: mylover
 * @Time: 11/02/2017.
 */
public class DramaInterfaceImpl implements DramaInterface {

    private SunWuKong swk;

    @Override
    public void injectSunWukong(SunWuKong sunWuKong) {
        this.swk = sunWuKong;
    }

    public void bringGoldHoop() {
        swk.say("曾经有一份真挚的爱情,放在我面前,我没有珍惜,现在......");
    }

}
