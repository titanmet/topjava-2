package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;


public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        List<UserMealWithExceed> userMealWithExceeds = getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        userMealWithExceeds.forEach(System.out::println);

        System.out.println(getFilteredWithExceededFor(mealList,LocalTime.of(8,0),LocalTime.of(11,0),2500));

    }

    public static List<UserMealWithExceed> getFilteredWithExceededFor(List<UserMeal> mealsList,LocalTime startTime, LocalTime endTime,int caloriesPerDay){
        Map<LocalDate,Integer> sumCalories = new HashMap<>();
        for(UserMeal userMeal: mealsList)
        sumCalories.merge(userMeal.getDateTime().toLocalDate(),userMeal.getCalories(),Integer::sum);

        List<UserMealWithExceed> userMealWithExceeds=new ArrayList<>();
        for(UserMeal userMeal: mealsList){
            if(TimeUtil.isBetween(userMeal.getDateTime().toLocalTime(),startTime,endTime))
                userMealWithExceeds.add(new UserMealWithExceed(userMeal.getDateTime(),userMeal.getDescription(),userMeal.getCalories(),
                        sumCalories.get(userMeal.getDateTime().toLocalDate())>caloriesPerDay));
        }


        return userMealWithExceeds;
    }



    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        Map<LocalDate, Integer> sumCalories = mealList.stream()
                .collect(Collectors.groupingBy(p -> p.getDateTime().toLocalDate(),
                        Collectors.summingInt(UserMeal::getCalories)));


        return mealList.stream()
                .filter(p -> TimeUtil.isBetween(p.getDateTime().toLocalTime(), startTime, endTime))
                .map(p -> new UserMealWithExceed(p.getDateTime(), p.getDescription(), p.getCalories(),
                        sumCalories.get(p.getDateTime().toLocalDate()) > caloriesPerDay))
                .collect(Collectors.toList());

    }


}
