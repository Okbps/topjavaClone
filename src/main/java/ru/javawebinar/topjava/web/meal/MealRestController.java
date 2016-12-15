package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.service.MealService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
@Controller
public class MealRestController {
    @Autowired
    private MealService mealService;

    @Autowired
    private AuthorizedUser user;

    public Meal getById(String sId){
        return mealService.getById(Integer.parseInt(sId), user.id());
    }

    public void remove(String sId){
        mealService.remove(Integer.parseInt(sId), user.id());
    }

    public List<MealWithExceed> getListWithExceed(
            String sDateStart,
            String sDateEnd,
            String sTimeStart,
            String sTimeEnd

    ){
        return mealService.getMealsWithExceed(
                sDateStart == null || sDateStart.isEmpty()  ? null : LocalDate.parse(sDateStart),
                sDateEnd == null || sDateEnd.isEmpty()      ? null : LocalDate.parse(sDateEnd),
                sTimeStart == null || sTimeStart.isEmpty()  ? null : LocalTime.parse(sTimeStart),
                sTimeEnd == null || sTimeEnd.isEmpty()      ? null : LocalTime.parse(sTimeEnd)
        );
    }

    public void updateMeal(String sId, String sDateTime, String sDescription, String sCalories){
        Meal meal = mealService.getById(Integer.parseInt(sId), user.id());
        meal.setDescription(sDescription);
        meal.setCalories(Integer.parseInt(sCalories));
        meal.setDateTime(LocalDateTime.parse(sDateTime));
    }

    public void saveMeal(String sDateTime, String sDescription, String sCalories){
        Meal meal = new Meal(
                LocalDateTime.parse(sDateTime),
                sDescription,
                Integer.parseInt(sCalories),
                user.id()
        );
        mealService.save(meal, user.id());
    }

}
