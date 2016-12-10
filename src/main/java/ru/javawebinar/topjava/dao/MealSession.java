package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.Collections;
import java.util.List;

/**
 * Created by Aspire on 08.12.2016.
 */
public class MealSession {
    private static List<Meal> meals = Collections.synchronizedList(MealsUtil.getMeals());

    synchronized public static void removeMeal(Meal meal){
        if(meals.contains(meal))
            meals.remove(meal);
    }

    synchronized public static void addMeal(Meal meal){
        meals.add(meal);
    }

    public static List<Meal> getMeals() {
        return meals;
    }

    private MealSession() {
    }
}
