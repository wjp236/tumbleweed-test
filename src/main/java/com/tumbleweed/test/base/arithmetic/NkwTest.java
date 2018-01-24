package com.tumbleweed.test.base.arithmetic;

import org.junit.Test;

import java.util.Scanner;

/**
 * 描述: nkw算法面试题
 *
 * @author: mylover
 * @Time: 14/01/2018.
 */
public class NkwTest {

    private static Scanner scanner = new Scanner(System.in);

    private static String message = "";

    private static boolean GetInputString() {
        message = scanner.nextLine();
        if (message.length() == 0) {
            return false;
        }
        return true;
    }

    public boolean find(int target, int [][] array) {



        return false;
    }


    /* 思路
    * 矩阵是有序的，从左下角来看，向上数字递减，向右数字递增，
    * 因此从左下角开始查找，当要查找数字比左下角数字大时。右移
    * 要查找数字比左下角数字小时，上移
    */
    @Test
    public void test1() {
        NkwTest nkwTest = new NkwTest();

        int target = 10;
        int [][] array = new int[5][5];

        nkwTest.find(10, array);
    }

}
