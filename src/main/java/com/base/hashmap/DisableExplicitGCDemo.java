package com.base.hashmap;

import java.nio.ByteBuffer;

/**
 * 描述:
 *
 * @author: mylover
 * @Time: 17/02/2017.
 */
public class DisableExplicitGCDemo {

    public static void main(String[] args) {
        for (int i = 0; i < 100000; i++) {
            ByteBuffer.allocateDirect(128);
        }
        System.out.println("Done");
    }

}
