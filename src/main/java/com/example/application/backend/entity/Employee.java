package com.example.application.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
public class Employee extends AbstractEntity{
    @NotEmpty
    private String Fullname;

    @NotNull
    private String Position;

    @NotNull
    private String Department;

    @NotNull
    @NotEmpty
    private String PhoneNumber;

    @NotNull
    @NotEmpty
    private String Gender;

    @NotNull
    @NotEmpty
    @Email
    private String Email;

    @NotNull
    @NotEmpty
    private double day_salary;

    public String getFullname() {
        return Fullname;
    }

    public void setFullname(String fullname) {
        Fullname = fullname;
    }

    public String getPosition() {
        return Position;
    }

    public void setPosition(String position) {
        Position = position;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public double getDay_salary() {
        return day_salary;
    }

    public void setDay_salary(double day_salary) {
        this.day_salary = day_salary;
    }
}
