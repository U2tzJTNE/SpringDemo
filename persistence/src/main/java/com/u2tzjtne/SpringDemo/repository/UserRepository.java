package com.u2tzjtne.SpringDemo.repository;

import com.u2tzjtne.SpringDemo.domain.IUserDao;
import com.u2tzjtne.SpringDemo.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Repository
public class UserRepository {

    public List<User> findAll() {
        //1.读取配置文件
        InputStream in;
        try {
            in = Resources.getResourceAsStream("SqlMapConfig.xml");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        //2.创建SqlSession工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        //3.使用工厂创建SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //4.使用sqlSession创建Dao接口的代理对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        List<User> users = userDao.findAll();
        sqlSession.close();
        //5.使用代理对象执行方法
        return users;
    }

    public boolean addUser(User user) {
        //1.读取配置文件
        InputStream in;
        try {
            in = Resources.getResourceAsStream("SqlMapConfig.xml");
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        //2.创建SqlSession工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        //3.使用工厂创建SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //4.使用sqlSession创建Dao接口的代理对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        userDao.addUser(user);
        sqlSession.commit();
        sqlSession.close();
        //5.使用代理对象执行方法
        return true;
    }
}
