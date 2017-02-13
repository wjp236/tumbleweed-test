package com.base.ioc;

import org.junit.Test;

/**
 * 描述:导演
 *
 * @author: mylover
 * @Time: 11/02/2017.
 */
public class Direct {

    //构造方法注入
    @Test
    public void directStructure() {
        //制定角色的扮演者
        SunWuKong swk = new WangJianPing();

        //注入到剧本
        DramaStructure dramaStructure = new DramaStructure(swk);

        dramaStructure.bringGoldHoop();

    }



    //属性注入
    @Test
    public void directAttribute() {
        //制定角色的扮演者
        SunWuKong swk = new WangJianPing();

        //注入到剧本
        DramaAttribute dramaAttribute = new DramaAttribute();
        dramaAttribute.setSunWukong(swk);

        dramaAttribute.bringGoldHoop();

    }


    //接口注入
    @Test
    public void directInterface() {

        //扮演者
        SunWuKong swk = new WangJianPing();

        DramaInterfaceImpl dramaInterface = new DramaInterfaceImpl();
        dramaInterface.injectSunWukong(swk);

        dramaInterface.bringGoldHoop();

    }




}
