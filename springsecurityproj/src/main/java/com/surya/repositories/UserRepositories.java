package com.surya.repositories;

import com.surya.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositories extends JpaRepository<User,Integer> {
    User findUserByUsername(String uname);
}
