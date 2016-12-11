package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Aspire on 11.12.2016.
 */
@Repository
public class InMemoryMealRepositoryImpl implements UserMealRepository{
//    private List<Meal> repository = new CopyOnWriteArrayList<>();
    private Map<Integer, Meal>repository = new ConcurrentHashMap<>();
    private AtomicInteger count;

    {
        count = new AtomicInteger(0);
        MealsUtil.getMeals().forEach(this::save);
    }

    @Override
    public Meal save(Meal meal) {
        if(meal.isNew())
            meal.setId(count.incrementAndGet());

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
    public Collection<Meal> getAll() {
        return repository.values();
    }
}
