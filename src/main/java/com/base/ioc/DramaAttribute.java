package com.base.ioc;

/**
 * 描述:属性注入
 *
 * @author: mylover
 * @Time: 11/02/2017.
 */
public class DramaAttribute {

    private SunWuKong swk;

    public void setSunWukong(SunWuKong swk) {
        this.swk = swk;
    }

    public void bringGoldHoop() {
        swk.say("曾经有一份真挚的爱情,放在我面前,我没有珍惜,现在......");
    }

}
