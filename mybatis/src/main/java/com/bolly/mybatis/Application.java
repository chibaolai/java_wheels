package com.bolly.mybatis;

import com.bolly.mybatis.entity.BizAccount;
import com.bolly.mybatis.mapper.BizAccountMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class Application {

    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BizAccountMapper bizAccountMapper = sqlSession.getMapper(BizAccountMapper.class);
        BizAccount bizAccount = bizAccountMapper.getByPhone("13644952636");
        System.out.println(bizAccount.getName());
    }
}
