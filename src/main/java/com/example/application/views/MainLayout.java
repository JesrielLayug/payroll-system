package com.example.application.views;

import com.example.application.backend.security.SecurityService;
import com.example.application.backend.service.SalaryService;
import com.example.application.views.employee.EmployeeView;
import com.example.application.views.report.ReportView;
import com.example.application.views.salary.SalaryView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

import java.util.HashMap;
import java.util.Map;

/**
 * The main view is a top-level placeholder for other views.
 */

@Route("app-layout-navbar")
public class MainLayout extends AppLayout {

    private static final Map<String, Class<? extends Component>> viewNameToRouteMap = new HashMap<>();
    private final SecurityService securityService;
    private H2 viewTitle;

    public MainLayout(SecurityService securityService) {
        this.securityService = securityService;

        addHeaderContent();
        createFooter();
    }

    private void addHeaderContent() {

        H1 title = new H1("Employee Payroll System");
        title.getStyle().set("font-size", "var(--lumo-font-size-l)")
                .set("left", "var(--lumo-space-l)").set("margin", "0")
                .set("position", "absolute");

        Tabs tabs = getTabs();

        String u = securityService.getAuthenticatedUser().getUsername();
        Button logout = new Button("Log out " + u, e -> securityService.logout());
        logout.getStyle().set("margin-right", "1em");

        addToNavbar(true, title, tabs, logout);
    }

    private Tabs getTabs() {
        Tabs tabs = new Tabs();
        tabs.getStyle().set("margin", "auto");
        tabs.add(createTab("Employee"), createTab("Salary"), createTab("Report"));
        return tabs;
    }

    static {
        viewNameToRouteMap.put("Employee", EmployeeView.class);
        viewNameToRouteMap.put("Salary", SalaryView.class);
        viewNameToRouteMap.put("Report", ReportView.class);
    }
    private Tab createTab(String viewName) {
        RouterLink link = new RouterLink();
        link.add(viewName);
        link.setRoute(getRouteClassForViewName(viewName));
        // Demo has no routes;
        link.setTabIndex(-1);

        viewTitle = new H2(viewName);

        return new Tab(link);
    }

    private Class<? extends Component> getRouteClassForViewName(String viewName) {
        return viewNameToRouteMap.get(viewName);
    }

    private Footer createFooter() {
        Footer layout = new Footer();

        return layout;
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        viewTitle.setText(getCurrentPageTitle());
    }

    private String getCurrentPageTitle() {
        PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
        return title == null ? "" : title.value();
    }

}
