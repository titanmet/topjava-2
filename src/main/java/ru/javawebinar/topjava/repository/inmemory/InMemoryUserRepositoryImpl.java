package ru.javawebinar.topjava.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class InMemoryUserRepositoryImpl implements UserRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryUserRepositoryImpl.class);
    private Map<Integer, User> repositoryUser = new HashMap<>();
    private AtomicInteger counterUser = new AtomicInteger(0);

    @Override
    public boolean delete(int id) {
        log.info("delete {}", id);
        repositoryUser.remove(id);
        return true;
    }

    @Override
    public User save(User user) {
        log.info("save {}", user);
        if(user.isNew()){
            user.setId(counterUser.incrementAndGet());
        }
        repositoryUser.put(user.getId(),user);
        return user;
    }

    @Override
    public User get(int id) {
        log.info("get {}", id);
        return repositoryUser.get(id);
    }

    @Override
    public List<User> getAll() {
        log.info("getAll");
        return new ArrayList<>(repositoryUser.values());
    }

    @Override
    public User getByEmail(String email) {
        log.info("getByEmail {}", email);
        User user1;
        user1 = repositoryUser.values().stream().filter(user -> email.equals(user.getEmail())).findFirst().get();
        return user1;
    }
}
