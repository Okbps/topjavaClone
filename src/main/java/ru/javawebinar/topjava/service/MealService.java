package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;

import java.util.List;

/**
 * Created by Aspire on 08.12.2016.
 */
public interface MealService {
    public void save(Meal meal, int userId);

    public Meal getById(int id);

    public void remove(int id, int userId);

    public List<MealWithExceed> getMealsWithExceed();
}
