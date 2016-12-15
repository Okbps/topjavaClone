package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Created by Aspire on 11.12.2016.
 */
@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Meal>repository = new ConcurrentHashMap<>();
    private AtomicInteger count;

    {
        count = new AtomicInteger(0);
        MealsUtil.getMeals().forEach(meal -> save(meal));
    }

    @Override
    public Meal save(Meal meal) {
        if(meal.isNew())
            meal.setId(count.getAndIncrement());

        return repository.put(meal.getId(), meal);
    }

    @Override
    public void delete(int id) {
        repository.remove(id);
    }

    @Override
    public Meal get(int id) {
        return repository.get(id);
    }

    @Override
    public Collection<Meal> getAll(Integer userId) {

        List<Meal> mealList;

        if(userId==null)
            mealList = new ArrayList<>(repository.values());
        else
            mealList = repository.values()
                    .stream()
                    .filter(meal -> meal.getUserId()==userId)
                    .collect(Collectors.toList());

        mealList.sort((Meal m1, Meal m2) -> m2.getDateTime().compareTo(m1.getDateTime()));
        return mealList;
    }
}
