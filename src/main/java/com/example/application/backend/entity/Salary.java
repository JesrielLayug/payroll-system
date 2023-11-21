package com.example.application.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
public class Salary extends AbstractEntity{

    @ManyToOne
    @JoinColumn(name = "employee_id")
    @NotNull
    private Employee employee;

    @NotNull
    private int daysOfWork;

    private double deduction = 0.0;

    private double bonus = 0.0;

    @NotNull
    private double tax;
    @NotNull
    private double totalSalary;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getDaysOfWork() {
        return daysOfWork;
    }

    public void setDaysOfWork(int daysOfWork) {
        this.daysOfWork = daysOfWork;
    }


    public double getDeduction() {
        return deduction;
    }

    public void setDeduction(double deduction) {
        this.deduction = deduction;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(double totalSalary) {
        this.totalSalary = totalSalary;
    }
}
