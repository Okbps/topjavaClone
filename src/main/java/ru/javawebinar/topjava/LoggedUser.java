package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Role;

import java.util.Set;

/**
 * Created by Aspire on 10.12.2016.
 */
public class LoggedUser {
    protected int id;
    protected boolean enabled;
    protected Set<Role> roles;

    public int getId() {
        return id;
    }
}
