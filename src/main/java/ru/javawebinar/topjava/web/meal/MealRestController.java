package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
public class MealRestController {
    protected final Logger log= LoggerFactory.getLogger(getClass());

    @Autowired
    private MealService service;

    public List<MealTo> getAll(){
        log.info("getAll meal");
        return MealsUtil.getWithExcess(service.getAll(AuthorizedUser.id()),AuthorizedUser.getCaloriesPerDay());
    }

    public List<MealTo> getFiltered(String startDateStr,String endDateStr,String startTimeStr,String endTimeStr){
        log.info("getFiltered meal");
        List<Meal> meals;
        if(startDateStr.equals("") && endDateStr.equals("")){
            meals=service.getAll(AuthorizedUser.id());
        }else{
            LocalDate startDate=startDateStr.equals("") ? LocalDate.MIN : LocalDate.parse(startDateStr);
            LocalDate endDate=endDateStr.equals("") ? LocalDate.MAX : LocalDate.parse(endDateStr);
            meals=service.getFiltered(startDate,endDate,AuthorizedUser.id());
        }
        if(startTimeStr.equals("") && endTimeStr.equals("")){
            return MealsUtil.getWithExcess(meals,AuthorizedUser.getCaloriesPerDay());
        }else {
            LocalTime startTime=startTimeStr.equals("")?LocalTime.MIN:LocalTime.parse(startTimeStr);
            LocalTime endTime=endTimeStr.equals("")?LocalTime.MAX:LocalTime.parse(endTimeStr);
            return  MealsUtil.getFilteredWithExcess(meals,startTime,endTime,AuthorizedUser.getCaloriesPerDay());
        }

    }

    public Meal get(int id){
        log.info("Meal get"+id);
        return service.get(id,AuthorizedUser.id());
    }

    public Meal create(Meal meal){
        meal.setId(null);
        meal.setUserId(AuthorizedUser.id());
        log.info("Meal create"+meal);
        return service.save(meal);
    }

    public void delete(int id){
        log.info("delete Meal"+id);
        service.delete(id,AuthorizedUser.id());
    }

    public void update(Meal meal){
        meal.setUserId(AuthorizedUser.id());
        log.info("update meal"+meal);
        service.update(meal);
    }



}