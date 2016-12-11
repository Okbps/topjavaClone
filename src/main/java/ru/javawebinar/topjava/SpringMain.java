package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.repository.mock.InMemoryMealRepositoryImpl;
import ru.javawebinar.topjava.repository.mock.InMemoryUserRepositoryImpl;
import ru.javawebinar.topjava.service.MealServiceImpl;
import ru.javawebinar.topjava.web.MealServlet;

import java.util.Arrays;

/**
 * Created by Aspire on 10.12.2016.
 */
public class SpringMain {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        System.out.println(Arrays.toString(context.getBeanDefinitionNames()));
//        InMemoryUserRepositoryImpl inMemoryUserRepositoryImpl = (InMemoryUserRepositoryImpl) context.getBean("inMemoryUserRepositoryImpl");
        InMemoryUserRepositoryImpl inMemoryUserRepository = context.getBean(InMemoryUserRepositoryImpl.class);
        InMemoryMealRepositoryImpl inMemoryMealRepository = context.getBean(InMemoryMealRepositoryImpl.class);
//        MealServlet mealServlet = new MealServlet();

        context.close();
    }
}
