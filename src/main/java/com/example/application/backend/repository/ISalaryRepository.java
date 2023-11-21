package com.example.application.backend.repository;

import com.example.application.backend.entity.Salary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISalaryRepository extends JpaRepository<Salary, Long> {
}
