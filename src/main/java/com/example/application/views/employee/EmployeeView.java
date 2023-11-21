package com.example.application.views.employee;

import com.example.application.backend.entity.Employee;
import com.example.application.backend.service.EmployeeService;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import jakarta.annotation.security.RolesAllowed;
import org.vaadin.crudui.crud.impl.GridCrud;


@PageTitle("Employee")
@Route(value = "Employee", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@RolesAllowed("ADMIN")
public class EmployeeView extends VerticalLayout {

    public EmployeeView(EmployeeService service) {
        setSizeFull();

        var grid = new GridCrud<>(Employee.class, service);
        grid.setSizeFull();

        // Setting up all the grid columns
        grid.getGrid().setColumns("id", "fullname", "email", "phoneNumber", "gender", "position", "department", "day_salary");
        grid.getGrid().getColumns().forEach(col -> col.setAutoWidth(true));
        grid.getGrid().getColumns().forEach(col -> col.setFlexGrow(2));

        // Setting up the form for adding
        grid.getCrudFormFactory().setVisibleProperties("fullname", "email", "phoneNumber", "gender" ,"position", "department", "day_salary");

        HorizontalLayout content = new HorizontalLayout(grid);
        content.setFlexGrow(2, grid);
        content.addClassName("content");
        content.setSizeFull();

        add(content);
    }





}
