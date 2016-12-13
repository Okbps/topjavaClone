package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.User;

import java.util.Collection;
import java.util.List;

/**
 * Created by Aspire on 10.12.2016.
 */
public interface UserRepository {
    User save(User user);

    void delete(int id);

    User get(int id);

    User getByEmail(String email);

    Collection<User> getAll();
}
