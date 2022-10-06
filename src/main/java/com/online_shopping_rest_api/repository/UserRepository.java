package com.online_shopping_rest_api.repository;

import com.online_shopping_rest_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUsername(String username);

//    User getUser(String name);
//    Iterable<User> getUsers();
//    Iterable<User> getUserByYear();
//    Iterable<User> getUserByMonth();
//    Iterable<User> getUserByWeek();
//    Iterable<User> getUserByDay();

}
