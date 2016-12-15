package ru.javawebinar.topjava.web;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.web.meal.MealRestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Aspire on 06.12.2016.
 */
public class MealServlet extends HttpServlet {

    private MealRestController controller;
    private String  sDateStart = null,
                    sDateEnd = null,
                    sTimeStart = null,
                    sTimeEnd = null;

//    private ConfigurableApplicationContext context;

    @Override
    public void init() throws ServletException {
        super.init();
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        controller = context.getBean(MealRestController.class);
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
            req.setAttribute("meal", controller.getById(req.getParameter("edit")));
            req.getRequestDispatcher("/mealEdit.jsp").forward(req, resp);
        }
        else if(req.getParameter("remove")!=null){
            controller.remove(req.getParameter("remove"));
            req.setAttribute("mealList", controller.getListWithExceed(sDateStart, sDateEnd, sTimeStart, sTimeEnd));
            req.getRequestDispatcher("/mealList.jsp").forward(req, resp);
        }
        else if(req.getParameter("new")!=null){
            req.getRequestDispatcher("/mealEdit.jsp").forward(req, resp);
        }
        else {
            req.setAttribute("mealList", controller.getListWithExceed(sDateStart, sDateEnd, sTimeStart, sTimeEnd));
            req.getRequestDispatcher("/mealList.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        if(req.getParameter("dateStart")!=null){
            sDateStart = req.getParameter("dateStart");
            sDateEnd = req.getParameter("dateEnd");
            sTimeStart = req.getParameter("timeStart");
            sTimeEnd = req.getParameter("timeEnd");
        }
        else if(req.getParameter("mealId").isEmpty()){
            controller.saveMeal(
                    req.getParameter("dateTime"),
                    req.getParameter("description"),
                    req.getParameter("calories")
            );
        }
        else {
            controller.updateMeal(
                    req.getParameter("mealId"),
                    req.getParameter("dateTime"),
                    req.getParameter("description"),
                    req.getParameter("calories")
            );
        }

        req.setAttribute("mealList", controller.getListWithExceed(sDateStart, sDateEnd, sTimeStart, sTimeEnd));
        req.getRequestDispatcher("/mealList.jsp").forward(req, resp);
    }

}
