package com.main;

import com.dao.PersonDao;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

        PersonDao personDao = context.getBean(PersonDao.class);
        personDao.performOperations();
        context.close();
    }
}
