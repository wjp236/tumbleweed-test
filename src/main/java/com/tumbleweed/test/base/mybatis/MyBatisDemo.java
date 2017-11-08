package com.tumbleweed.test.base.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * 描述: test mybatis
 *
 * @author: mylover
 * @Time: 06/11/2017.
 */
public class MyBatisDemo {

    private static SqlSessionFactory sqlSessionFactory;

    public static SqlSession getSqlSession() throws FileNotFoundException {
        InputStream configFile = new FileInputStream("");

        SqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(null);

        return sqlSessionFactory.openSession();
    }


}
