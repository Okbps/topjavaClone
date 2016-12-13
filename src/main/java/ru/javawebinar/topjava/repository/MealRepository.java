package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.util.Collection;
import java.util.List;

/**
 * Created by Aspire on 10.12.2016.
 */
public interface MealRepository {
    Meal save(Meal meal, Integer userId);

    void delete(int id, Integer userId);

    Meal get(int id);

    Collection<Meal> getAll(Integer userId);
}
