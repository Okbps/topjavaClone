package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.repository.mock.InMemoryMealRepositoryImpl;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

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
        repository.save(meal, userId);
    }

    public Meal getById(int id){
        return repository.get(id);
    }

    public void remove (int id, int userId) throws NotFoundException {
        repository.delete(id, userId);
    }

    @Override
    public List<MealWithExceed> getMealsWithExceed() {
        return MealsUtil.getFilteredWithExceeded(
                new ArrayList<>(repository.getAll(null)),
                null,
                null,
                2000
        );
    }

}
