package com.erdon.jsonandajax;

import com.erdon.dao.UserDao;
import com.erdon.entity.Book;
import com.erdon.entity.User;
import com.erdon.service.BookService;
import com.erdon.service.UserService;
import com.erdon.service.impl.BookServiceImpl;
import com.erdon.service.impl.UserServiceImpl;
import com.erdon.utils.MbUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class JsonTest {
//    1、javaBean与json的转换
    @Test
    public void jsonTest1(){
        Person person1 = new Person("王大錘",55);
        Person person2 = new Person("擎天柱",555);
        Gson gson = new Gson();
//        javaBean转json字符串
        String s = gson.toJson(person1);
        System.out.println(s);
//      json字符串转javaBean
        Person person = gson.fromJson(s, Person.class);
        System.out.println(person);

    }
//    List<>转json
    @Test
    public void listToJson(){
        List<Person> list = new ArrayList<>();
        Person person1 = new Person("王大錘",55);
        Person person2 = new Person("擎天柱",555);
        list.add(person1);
        list.add(person2);
        System.out.println(list);
        Gson gson = new Gson();
        String s = gson.toJson(list);
        System.out.println(s);
        List<Person> o = gson.fromJson(s, new TypeToken<List<Person>>() {}.getType());
        System.out.println(o);
    }
//    json与map转换
    @Test
    public void jsonToMap(){
        Map<Integer,Person> map = new ConcurrentHashMap<>();
        Person person1 = new Person("王大錘",55);
        Person person2 = new Person("擎天柱",555);
        map.put(1,person1);
        map.put(2,person2);
        System.out.println(map);
        Gson gson = new Gson();
        String s = gson.toJson(map);
        System.out.println(s);
        ConcurrentHashMap<Integer,Person> o = gson.fromJson(s, new TypeToken<ConcurrentHashMap<Integer, Person>>() {}.getType());
        System.out.println(o);

    }

    @Test
    public void mbTest1() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
//        Object sqlSessionFactory = ac.getBean("sqlSessionFactory");
//        System.out.println(sqlSessionFactory);
        UserService userService = (UserService) ac.getBean("userService");
       // BookService bookservice = (BookService) ac.getBean("bookservice");
        List<User> users = userService.queryUsers();
        users.forEach(user -> System.out.println(user));
        System.out.println(123123);
    }


}
