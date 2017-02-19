package com.base.reflect;

/**
 * 描述:
 *
 * @author: mylover
 * @Time: 18/02/2017.
 */
public class TestBean {

    public final static String DEFAULT="default";
    private String word;

    public TestBean() {
        word=DEFAULT;
    }
    public String getWord(){
        return word;
    }

    public void setWord(String word){
        this.word=word;
    }

}
