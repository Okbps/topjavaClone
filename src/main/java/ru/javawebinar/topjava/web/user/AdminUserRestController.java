package ru.javawebinar.topjava.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.service.UserService;

/**
 * Created by Aspire on 10.12.2016.
 */
@Controller
public class AdminUserRestController {

    @Autowired
    private UserService service;
}
