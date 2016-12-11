package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.repository.mock.InMemoryUserRepositoryImpl;

import java.util.Arrays;

/**
 * Created by Aspire on 10.12.2016.
 */
public class SpringMain {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        System.out.println(Arrays.toString(context.getBeanDefinitionNames()));
        InMemoryUserRepositoryImpl inMemoryUserRepositoryImpl = (InMemoryUserRepositoryImpl) context.getBean("inMemoryUserRepositoryImpl");
        inMemoryUserRepositoryImpl = context.getBean(InMemoryUserRepositoryImpl.class);
        context.close();
    }
}
