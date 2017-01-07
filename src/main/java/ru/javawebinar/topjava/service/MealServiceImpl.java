package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.repository.mock.InMemoryMealRepositoryImpl;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aspire on 08.12.2016.
 */
@Service
public class MealServiceImpl implements MealService{

    @Autowired
    private InMemoryMealRepositoryImpl repository;

    public void save(Meal meal, int userId) throws NotFoundException {
        if(meal.getUserId()!=userId)
            throw new NotFoundException("Access denied or meal doesn't exist");
        else
            repository.save(meal);
    }

    public Meal getById(int id, int userId) throws NotFoundException {
        Meal meal = repository.get(id);
        if(meal==null || meal.getUserId()!=userId)
            throw new NotFoundException("Access denied or meal doesn't exist");
        else
            return meal;
    }

    public void remove (int id, int userId) throws NotFoundException {
        Meal meal = repository.get(id);
        if(meal==null || meal.getUserId()!=userId)
            throw new NotFoundException("Access denied or meal doesn't exist");

        repository.delete(id);
    }

    @Override
    public List<MealWithExceed> getMealsWithExceed(
            Integer userId,
            LocalDate starDate,
            LocalDate endDate,
            LocalTime startTime,
            LocalTime endTime
    ) {
        return MealsUtil.getFilteredWithExceeded(
                new ArrayList<>(repository.getAll(userId)),
                starDate,
                endDate,
                startTime,
                endTime,
                MealsUtil.DEFAULT_CALORIES_PER_DAY
        );
    }

}
