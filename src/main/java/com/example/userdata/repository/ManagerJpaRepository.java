package com.example.userdata.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.userdata.model.Manager;

@Repository
public interface ManagerJpaRepository extends JpaRepository<Manager, Integer> {
}
