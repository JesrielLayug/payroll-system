package com.example.application.views.salary;

import com.example.application.backend.entity.Employee;
import com.example.application.backend.entity.Salary;
import com.example.application.backend.service.EmployeeService;
import com.example.application.backend.service.SalaryService;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import jakarta.annotation.security.RolesAllowed;
import org.vaadin.crudui.crud.impl.GridCrud;

@PageTitle("Salary")
@Route(value = "Salary", layout = MainLayout.class)
@RouteAlias(value = "Salary", layout = MainLayout.class)
@RolesAllowed("ADMIN")
public class SalaryView extends VerticalLayout {

    SalaryService salaryService;

    public SalaryView( SalaryService salaryService, EmployeeService employeeService) {
        this.salaryService = salaryService;

        setSizeFull();

        var grid = new GridCrud<>(Salary.class, salaryService);
        grid.setSizeFull();

        grid.getGrid().setColumns("id");
        grid.getGrid().addColumn(employee -> employee.getEmployee().getFullname()).setHeader("Employee");
        grid.getGrid().addColumns("daysOfWork", "deduction", "bonus", "tax", "totalSalary");
        grid.getGrid().getColumns().forEach(col -> col.setAutoWidth(true));
        grid.getGrid().getColumns().forEach(col -> col.setFlexGrow(2));

        grid.getCrudFormFactory().setFieldProvider("employee", employee -> {
                ComboBox<Employee> employeeComboBox = new ComboBox<>();
                employeeComboBox.setItems(employeeService.findAll());
                employeeComboBox.setItemLabelGenerator(Employee::getFullname);
                return employeeComboBox;
                });

        grid.getCrudFormFactory().setVisibleProperties("employee", "daysOfWork", "deduction", "bonus", "tax");
        grid.getCrudFormFactory().setUseBeanValidation(true);
        add(grid);

    }

}
