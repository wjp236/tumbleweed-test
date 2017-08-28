package com.base.arithmetic;

import org.junit.Test;

/**
 * 描述:阶乘运算
 *
 * @author: mylover
 * @Time: 28/08/2017.
 */
public class Test1 {

    @Test
    public void test1() {
        int n = 5;
        int num1 = 1;

        //大数字计算
        for (int i = 1; i <= n; i++) {
            num1 = i*num1;
        }
        System.out.println(num1);

    }

    @Test
    public void test2() {

        int[] nList = new int[6];
        nList[nList.length -1] = 2;
        nList[nList.length -2] = 3;
        nList[nList.length -1] = 7;

        int num = 16;

        for (int i = 0; i < nList.length; i++) {
            nList[i] *= num;
        }

        for (int i = nList.length - 1; i > 0; i--) {
            nList[i - 1] += nList[i]/10;
            nList[i] = nList[i]%10;
        }

        for (int i = 0; i < nList.length; i++) {
            System.out.print(nList[i]);
        }
        System.out.println();
    }

    @Test
    public void test3() {

        int[] nList = new int[100];
        int m = 50;

        nList[nList.length - 1] = 1;

        for (int j = 1; j <= m; j++) {
            nList = this.multiplication(nList, j);
        }

        for (int i = 0; i < nList.length; i++) {
            System.out.print(nList[i]);
        }
        System.out.println();
    }


    public static int[] multiplication(int[] nList, int num) {
        for (int i = 0; i < nList.length; i++) {
            nList[i] *= num;
        }

        for (int i = nList.length - 1; i > 0; i--) {
            nList[i - 1] += nList[i]/10;
            nList[i] = nList[i]%10;
        }
        return nList;
    }



}
