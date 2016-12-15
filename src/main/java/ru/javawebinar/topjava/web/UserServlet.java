package ru.javawebinar.topjava.web;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.web.meal.MealRestController;
import ru.javawebinar.topjava.web.user.ProfileRestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Aspire on 03.12.2016.
 */
public class UserServlet extends HttpServlet {

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        response.sendRedirect("userList.jsp");
        //        request.getRequestDispatcher("/userList.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(req.getParameter("user")!=null) {
            ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-app.xml");
            AuthorizedUser user = context.getBean(AuthorizedUser.class);
            user.setId(Integer.parseInt(req.getParameter("user")));

            MealRestController controller = context.getBean(MealRestController.class);
            req.setAttribute("mealList", controller.getListWithExceed(null, null, null, null));
            context.close();
        }
        resp.sendRedirect("/meals");
//        req.getRequestDispatcher("/mealList.jsp").forward(req, resp);
    }
}
