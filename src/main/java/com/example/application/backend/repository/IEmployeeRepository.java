package com.example.application.backend.repository;

import com.example.application.backend.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findById(long id);
}
