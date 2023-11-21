package com.example.application.backend.service;

import com.example.application.backend.entity.Salary;
import com.example.application.backend.repository.IEmployeeRepository;
import com.example.application.backend.repository.ISalaryRepository;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;

import java.util.Collection;
import java.util.List;

@Service
public class SalaryService implements CrudListener<Salary> {

    private final ISalaryRepository salaryRepository;
    private final IEmployeeRepository employeeRepository;

    public SalaryService(ISalaryRepository salaryRepository, IEmployeeRepository employeeRepository) {
        this.salaryRepository = salaryRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Salary> findAll() {
        return salaryRepository.findAll();
    }

    @Override
    public Salary add(Salary salary) {
        salary.setTotalSalary((salary.getEmployee().getDay_salary() * salary.getDaysOfWork())
               + salary.getBonus() - salary.getTax() - salary.getDeduction());
        return salaryRepository.save(salary);
    }

    @Override
    public Salary update(Salary salary) {
        salary.setTotalSalary((salary.getEmployee().getDay_salary() * salary.getDaysOfWork())
                + salary.getBonus() - salary.getTax() - salary.getDeduction());
        return salaryRepository.save(salary);
    }

    @Override
    public void delete(Salary salary) {
        salaryRepository.delete(salary);
    }

}
