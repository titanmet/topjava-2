package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public MealServiceImpl(MealRepository repository) {
        this.repository = repository;
    }

    @Override
    public Meal save(Meal meal) {
        return repository.save(meal);
    }

    @Override
    public void update(Meal meal) {
        save(meal);
    }


    @Override
    public void delete(int id, int userId) throws NotFoundException {
        repository.delete(id,userId);
    }

    @Override
    public Meal get(int id, int userId) throws NotFoundException {
        return repository.get(id,userId);
    }

    @Override
    public List<Meal> getAll(int userId) {
        return repository.getAll(userId);
    }

    @Override
    public List<Meal> getFiltered(LocalDate startDate, LocalDate endDate, int userId) {
        return repository.getFiltered(startDate,endDate,userId);
    }

    }