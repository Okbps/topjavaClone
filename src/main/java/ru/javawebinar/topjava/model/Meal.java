package ru.javawebinar.topjava.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * GKislin
 * 11.01.2015.
 */
public class Meal extends BaseEntity{
    private LocalDateTime dateTime;

    private String description;

    private int calories;

    private Integer id;

    private final Integer userId;

    public Meal(LocalDateTime dateTime, String description, int calories, int userId) {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.userId = userId;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public boolean isNew(){
        return id==null;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public Integer getId() {
        return this.id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }
}
