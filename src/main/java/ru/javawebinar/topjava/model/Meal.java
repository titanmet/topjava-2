package ru.javawebinar.topjava.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class Meal extends HashId{

    private final LocalDateTime dateTime;

    private final String description;

    private final int calories;

    public Meal(){
        this(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES),"",0);
    }

    public Meal(LocalDateTime dateTime, String description, int calories) {
        super(0);
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
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
