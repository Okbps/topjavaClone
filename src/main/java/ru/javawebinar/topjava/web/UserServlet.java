package ru.javawebinar.topjava.web;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.web.user.ProfileRestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

/**
 * Created by Aspire on 03.12.2016.
 */
public class UserServlet extends HttpServlet {

    private ProfileRestController controller;

    @Override
    public void init() throws ServletException {
        super.init();
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        controller = context.getBean(ProfileRestController.class);
        context.close();
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        response.sendRedirect("userList.jsp");
        //        request.getRequestDispatcher("/userList.jsp").forward(request, response);
    }
}
