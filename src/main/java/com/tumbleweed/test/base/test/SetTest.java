package com.tumbleweed.test.base.test;

import org.apache.http.client.ClientProtocolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class SetTest {

    public Logger log = LogManager.getLogger(SetTest.class);


    @Test
    public void set() throws ClientProtocolException,
            NoSuchAlgorithmException, IOException {
        Set<String> set = new TreeSet<String>();
        set.add("a");
        set.add("b");
        set.add("c");
        set.add("d");
        set.add("e");
        set.add("A");

        Iterator<String> it = set.iterator();
        for (int i = 0; i < set.size(); i++) {
            log.info(it.next());
        }
    }


}
