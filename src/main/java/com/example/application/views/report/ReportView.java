package com.example.application.views.report;

import ar.com.fdvs.dj.domain.AutoText;
import ar.com.fdvs.dj.domain.CustomExpression;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.builders.GroupBuilder;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import ar.com.fdvs.dj.domain.entities.columns.PropertyColumn;
import com.example.application.backend.entity.Employee;
import com.example.application.backend.entity.Salary;
import com.example.application.backend.service.EmployeeService;
import com.example.application.backend.service.SalaryService;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import jakarta.annotation.security.RolesAllowed;
import org.vaadin.reports.PrintPreviewReport;

import java.time.LocalDateTime;
import java.util.Map;

@Route("Report")
@RolesAllowed("ADMIN")
public class ReportView extends VerticalLayout {
    private final EmployeeService employeeService;
    private final SalaryService salaryService;

    public ReportView(SalaryService salaryService, EmployeeService employeeService){
        this.employeeService = employeeService;
        this.salaryService = salaryService;

        PrintPreviewReport<Salary> report = new PrintPreviewReport<>(Salary.class, "daysOfWork", "deduction", "bonus", "totalSalary");
        report.getReportBuilder().setTitle("SALARY REPORT");
        report.getReportBuilder().addColumn(employeeColumn());

        report.setItems(salaryService.findAll());


        add(
                report
        );
    }

    private AbstractColumn employeeColumn() {
        AbstractColumn column = ColumnBuilder.getNew()
                .setColumnProperty("employee", Employee.class.getName()) // Use the correct type for the employee property
                .setTitle("Employee") // Column title
                .setWidth(85) // Set width as needed
                .setCustomExpression(new CustomExpression() {
                    @Override
                    public Object evaluate(Map fields, Map variables, Map parameters) {
                        // Get the Employee object directly
                        Employee employee = (Employee) fields.get("employee");

                        // Return the full name of the employee
                        return employee != null ? employee.getFullname() : "N/A";
                    }

                    @Override
                    public String getClassName() {
                        return String.class.getName();
                    }
                })
                .build();

        return column;
    }
}
