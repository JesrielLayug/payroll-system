package com.example.application.component;

import com.example.application.backend.entity.Employee;
import com.example.application.backend.entity.Salary;
import com.example.application.backend.service.EmployeeService;
import com.example.application.backend.service.SalaryService;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.shared.Registration;

import java.util.List;

public class SalaryForm extends FormLayout {
    ComboBox<Employee> employee = new ComboBox<>("Employee");
    TextField daysOfWork = new TextField("Days of Work");
    TextField deduction = new TextField("Deduction");
    TextField bonus = new TextField("Bonus");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");


    BeanValidationBinder<Salary> binder = new BeanValidationBinder<>(Salary.class);
    SalaryService salaryService;

    public SalaryForm(List<Employee> employeeList, SalaryService salaryService){
        this.salaryService = salaryService;
        // Property binding to all the fields.
        binder.bindInstanceFields(this);

        employee.setItems(employeeList);
        employee.setItemLabelGenerator(Employee::getFullname);

        add(
                employee,
                daysOfWork,
                deduction,
                bonus,
                createButtonsLayout());
    }

    public void setSalary(Salary salary){
//        binder.setBean(salary.setTax(0.10), salary.setTotalSalary((salary::getDaysOfWork() * salary::getEmployee::getDay_salary()) - (salay::getTax() + salary::getDeduction()) + salary::getBonus()));
            binder.setBean(salary);
    }

    public void addSalary(Salary salary){
        salary = binder.getBean();
        salary.setTax(0.10);
        salary.setTotalSalary((salary.getDaysOfWork() * salary.getEmployee().getDay_salary()) - (salary.getTax() + salary.getDeduction()) + salary.getBonus());
        Salary finalSalary = salary;
        save.addClickListener(event -> salaryService.add(finalSalary));
    }


//    @Override
//    public void setTax(double tax) {
//        super.setTax(0.10);
//    }
//
//    @Override
//    public void setTotalSalary(double totalSalary) {
//        super.setTotalSalary((getDaysOfWork() * getEmployee().getDay_salary()) - (getTax() + getDeduction()) + getBonus());
//    }
//});

    private HorizontalLayout createButtonsLayout(){
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        save.addClickShortcut(Key.ESCAPE);

        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new DeleteEvent(this, binder.getBean())));
        close.addClickListener(event -> fireEvent(new CloseEvent(this)));

        binder.addStatusChangeListener(event -> save.setEnabled(binder.isValid()));

        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {
        if(binder.isValid()){
            fireEvent(new SaveEvent(this, binder.getBean()));
        }
    }


    // Event Class
    public static abstract class SalaryFormEvent extends ComponentEvent<SalaryForm>{
        private final Salary salary;
        protected SalaryFormEvent(SalaryForm source, Salary salary){
            super(source, false);
            this.salary = salary;
        }

        public Salary getSalary() {
            return salary;
        }
    }

    public static class SaveEvent extends SalaryFormEvent {
        SaveEvent(SalaryForm source, Salary salary){
            super(source, salary);
        }
    }

    public static class DeleteEvent extends SalaryFormEvent {
        DeleteEvent(SalaryForm source, Salary salary) {
            super(source, salary);
        }
    }

    public static class CloseEvent extends SalaryFormEvent {
        CloseEvent(SalaryForm source) {
            super(source, null);
        }
    }

    public Registration addDeleteListener(ComponentEventListener<DeleteEvent> listener) {
        return addListener(DeleteEvent.class, listener);
    }

    public Registration addSaveListener(ComponentEventListener<SaveEvent> listener) {
        return addListener(SaveEvent.class, listener);
    }
    public Registration addCloseListener(ComponentEventListener<CloseEvent> listener) {
        return addListener(CloseEvent.class, listener);
    }

}
