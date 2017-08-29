package com.tumbleweed.test.base.arithmetic;

import org.junit.Test;

/**
 * 描述:查找
 *
 * @author: mylover
 * @Time: 29/08/2017.
 */
public class Test4 {

    //二分查找发
    @Test
    public void test1() {
        int[] ints = {2, 3, 4, 5, 12, 17, 20};
        int num = 18;
        int s = 0;//开始
        int e = ints.length - 1;//结束
        int m = 0;//中间点

        while (s <= e) {
            m = (s + e) / 2;
            if (num == ints[m]) {
                System.out.println("get:" + m);
                return;
            } else if (num < ints[m]) {//左边
                e = m - 1;
            } else {//右边
                s = m + 1;
            }
        }
        System.out.println("没找到");
    }
}
