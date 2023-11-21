package com.example.application.backend.service;

import com.example.application.backend.entity.Employee;
import com.example.application.backend.repository.IEmployeeRepository;
import com.example.application.backend.repository.ISalaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;

import java.util.Collection;
import java.util.List;

@Service
public class EmployeeService implements CrudListener<Employee> {

    private final IEmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(IEmployeeRepository employeeRepository)
    {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee add(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee update(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void delete(Employee employee) {
        employeeRepository.delete(employee);
    }

    public Employee getById(long id){
        var employee = employeeRepository.findById(id);
        return employee;
    }


}
