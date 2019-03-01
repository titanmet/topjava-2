package ru.javawebinar.topjava.repository.jpa;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaMealRepositoryImpl implements MealRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Meal save(Meal meal, int userId) {
        if (meal.isNew()) {
            User ref = em.getReference(User.class, userId);
            meal.setUser(ref);
            em.persist(meal);
        } else {
            Query query = em.createNamedQuery(Meal.UPDATE);
            query.setParameter("description", meal.getDescription());
            query.setParameter("calories", meal.getCalories());
            query.setParameter("date_time", meal.getDateTime());
            query.setParameter("id", meal.getId());
            query.setParameter("userId", meal.getUser());
            if (query.executeUpdate() == 0) return null;
        }
        return meal;
    }

    @Override
    public boolean delete(int id, int userId) {
        Query query = em.createNamedQuery(Meal.DELETE);
        query.setParameter("id", id);
        query.setParameter("userId", userId);
        List<Meal> meals = query.getResultList();
        return query.executeUpdate() != 0;
    }

    @Override
    public Meal get(int id, int userId) {
        Query query = em.createNamedQuery(Meal.GET);
        query.setParameter("id", id);
        query.setParameter("userId", userId);
        List<Meal> meals = query.getResultList();
        return DataAccessUtils.singleResult(meals);
    }

    @Override
    public List<Meal> getAll(int userId) {
        return em.createNamedQuery(Meal.ALL, Meal.class).setParameter("userId", userId).getResultList();
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        Query query = em.createNamedQuery(Meal.BETWEEN, Meal.class);
        query.setParameter("userId", userId);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        return query.getResultList();
    }
}