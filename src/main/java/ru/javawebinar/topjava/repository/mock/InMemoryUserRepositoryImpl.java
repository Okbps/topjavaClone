package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Created by Aspire on 10.12.2016.
 */
@Repository
public class InMemoryUserRepositoryImpl implements UserRepository{
    private Map<String, User> repository = new ConcurrentHashMap<>();
    private AtomicInteger count;

    {
        count = new AtomicInteger(0);

        User u1 = new User("Eugene", "ep@gmail.com", "4444", Role.ROLE_ADMIN, Role.ROLE_USER);
        save(u1);

        User u2 = new User("Nataly", "bn@gmail.com", "1111", Role.ROLE_USER);
        save(u2);
    }

    @Override
    public User save(User user) {
        if(user.isNew())
            user.setId(count.getAndIncrement());

        return repository.put(user.getName(), user);
    }

    @Override
    public void delete(int id) {
        repository.entrySet().removeIf(entry -> entry.getValue().getId()==id);
    }

    @Override
    public User get(int id) {
        return repository.get(id);
    }

    @Override
    public User getByEmail(String email) {
        Collection<User>users = getAll();

        return users
            .stream()
            .filter(user -> user.getEmail().equals(email))
            .findFirst()
            .orElse(null);
    }

    @Override
    public Collection<User> getAll() {
        List<User>userList = new ArrayList<>(repository.values());
        userList.sort((u1, u2) -> u1.getName().compareTo(u2.getName()));
        return userList;
    }
}
