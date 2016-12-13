package ru.javawebinar.topjava.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.service.MealServiceImpl;
import ru.javawebinar.topjava.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Created by Aspire on 06.12.2016.
 */
public class MealServlet extends HttpServlet {

    private MealServiceImpl mealService;

//    private ConfigurableApplicationContext context;

    @Override
    public void init() throws ServletException {
        super.init();
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        mealService = context.getBean(MealServiceImpl.class);
        context.close();
    }

    @Override
    public void destroy() {
        super.destroy();
//        context.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        if(req.getParameter("edit")!=null) {
            req.setAttribute("meal", mealService.getById(Integer.parseInt(req.getParameter("edit"))));
            req.getRequestDispatcher("/mealEdit.jsp").forward(req, resp);
        }
        else if(req.getParameter("remove")!=null){
            mealService.remove(Integer.parseInt(req.getParameter("remove")), 0);
            req.setAttribute("mealList", mealService.getMealsWithExceed());
            req.getRequestDispatcher("/mealList.jsp").forward(req, resp);
        }
        else if(req.getParameter("new")!=null){
            req.getRequestDispatcher("/mealEdit.jsp").forward(req, resp);
        }
        else {
            req.setAttribute("mealList", mealService.getMealsWithExceed());
            req.getRequestDispatcher("/mealList.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        if(req.getParameter("user")!=null){
        }


        if(req.getParameter("mealId").isEmpty()){
            Meal meal = new Meal(
                    LocalDateTime.parse(req.getParameter("dateTime")),
                    req.getParameter("description"),
                    Integer.parseInt(req.getParameter("calories")),
                    0
            );
            mealService.save(meal, 0);
        }
        else {
            Meal meal = mealService.getById(Integer.parseInt(req.getParameter("mealId")));
            meal.setDescription(req.getParameter("description"));
            meal.setCalories(Integer.parseInt(req.getParameter("calories")));
            meal.setDateTime(LocalDateTime.parse(req.getParameter("dateTime")));
        }

        req.setAttribute("mealList", mealService.getMealsWithExceed());
        req.getRequestDispatcher("/mealList.jsp").forward(req, resp);
    }

}
