package ru.javawebinar.topjava.service;

import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Service
public class MealServiceImpl implements MealService {

    private MealRepository repository;


    @Override
    public Meal save(Meal meal) {
        return null;
    }

    @Override
    public void update(Meal meal) {

    }


    @Override
    public void delete(int id, int userId) throws NotFoundException {

    }

    @Override
    public Meal get(int id, int userId) throws NotFoundException {
        return null;
    }

    @Override
    public List<Meal> getAll(int userId) {
        return null;
    }

    @Override
    public List<Meal> getFiltered(LocalDate startDate, LocalDate endDate, int userId) {
        return null;
    }

    }