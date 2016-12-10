package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;

import java.util.List;

/**
 * Created by Aspire on 08.12.2016.
 */
public interface MealService {
    public void add(Meal meal);

    public Meal getById(int id);

    public void remove(int id);

    public List<MealWithExceed> getMealsWithExceed();
}
