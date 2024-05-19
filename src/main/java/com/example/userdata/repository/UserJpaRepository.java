package com.example.userdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.userdata.model.*;

import java.util.List;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Integer> {
    List<User> findByManager(Manager manager);
    User findByMobileNumber(String mobileNumber);
}

