package com.base.ioc;

/**
 * 描述:构造方法注入
 *
 * @author: mylover
 * @Time: 11/02/2017.
 */
public class DramaStructure {

    private SunWuKong swk;

    public DramaStructure(SunWuKong swk) {
        this.swk = swk;
    }

    public void bringGoldHoop() {
        swk.say("曾经有一份真挚的爱情,放在我面前,我没有珍惜,现在......");
    }

}
