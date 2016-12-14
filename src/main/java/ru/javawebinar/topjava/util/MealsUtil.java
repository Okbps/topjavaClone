package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * GKislin
 * 31.05.2015.
 */
public class MealsUtil {

    public static final int DEFAULT_CALORIES_PER_DAY = 2000;

    public static void main(String[] args) {
        List<Meal> meals = getMeals();
        List<MealWithExceed> filteredMealsWithExceeded = getFilteredWithExceeded(
                meals,
                null,
                null,
                LocalTime.of(7, 0),
                LocalTime.of(12, 0),
                2000
        );
        filteredMealsWithExceeded.forEach(System.out::println);
    }

    public static List<Meal> getMeals() {
        List<Meal>meals = new ArrayList<>();

        meals.add(new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500, 1));
        meals.add(new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000, 1));
        meals.add(new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500, 1));
        meals.add(new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000, 1));
        meals.add(new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500, 2));
        meals.add(new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510, 2));

        return meals;
    }

    public static List<MealWithExceed> getFilteredWithExceeded(
            List<Meal> meals,
            LocalDate startDate,
            LocalDate endDate,
            LocalTime startTime,
            LocalTime endTime,
            int caloriesPerDay) {
        Map<LocalDate, Integer> caloriesSumByDate = meals.stream()
                .collect(
                        Collectors.groupingBy(Meal::getDate, Collectors.summingInt(Meal::getCalories))
//                      Collectors.toMap(Meal::getDate, Meal::getCalories, Integer::sum)
                );


        return meals.stream()
                .filter(meal -> TimeUtil.isBetweenTime(
                        meal.getTime(),
                        startTime==null ? LocalTime.MIN : startTime,
                        endTime==null ? LocalTime.MAX : endTime
                        )
                )
                .filter(meal -> TimeUtil.isBetweenDate(
                        meal.getDate(),
                        startDate==null ? LocalDate.MIN.MIN : startDate,
                        endDate==null ? LocalDate.MAX : endDate
                        )
                )
                .map(meal ->
                        new MealWithExceed(
                                meal.getDateTime(),
                                meal.getDescription(),
                                meal.getCalories(),
                                caloriesSumByDate.get(meal.getDate()) > caloriesPerDay,
                                meal.getId(),
                                meal.getUserId()))
                .collect(Collectors.toList());
    }
}