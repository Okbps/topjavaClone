package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.dao.MealSession;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Aspire on 08.12.2016.
 */
public class MealServiceImpl implements MealService{

    public void add(Meal meal){
        MealSession.addMeal(meal);
    }

    public Meal getById(int id){
        List<Meal>mealsById = MealSession.getMeals()
                .stream().filter(meal -> meal.getId()==id)
                .collect(Collectors.toList());

        if(mealsById.size()>0)
            return mealsById.get(0);
        else
            return null;
    }

    public void remove(int id){
        MealSession.removeMeal(getById(id));
    }

    @Override
    public List<MealWithExceed> getMealsWithExceed() {
        return MealsUtil.getFilteredWithExceeded(MealSession.getMeals(), null, null, 2000);
    }

}
