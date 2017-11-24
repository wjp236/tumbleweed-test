package com.tumbleweed.test.base.arithmetic;

import org.junit.Test;

/**
 * 描述:插入排序
 *
 * @author: mylover
 * @Time: 29/08/2017.
 */
public class Test3 {

    @Test
    public void test1() {
        int[] ints = {2, 9, 6};

        for (int i = 1; i < ints.length; i++) {
            if (ints[i] < ints[i - 1]) {
                for (int j = 0; j < i; j++) {
                    if (ints[i] < ints[j]) {
                        int temp = ints[i];
                        for (int k = i - 1; k >= j; k--) {
                            ints[k + 1] = ints[k];
                        }
                        ints[j] = temp;
                        break;
                    }
                }
            }
        }


        for (int i = 0; i < ints.length; i++) {
            System.out.println(ints[i]);
        }
    }

    @Test
    public void test2() {
        //比较一次，移动一次，效率更高
        int[] ints = {2, 9, 6, 8};
        for (int i = 1; i < ints.length; i++) {
            if (ints[i] < ints[i - 1]) {
                for (int j = i; j > 0; j--) {
                    if (ints[j] < ints[j - 1]) {
                        int temp = ints[j];
                        ints[j] = ints[j - 1];
                        ints[j - 1] = temp;
                    }
                }
            }
        }

        for (int i = 0; i < ints.length; i++) {
            System.out.println(ints[i]);
        }

    }

}
