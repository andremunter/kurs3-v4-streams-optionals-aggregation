package se.chasacademy.fjax26.sundsvall;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EmployeeTestData {

    public static List<Employee> getEmployees() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("E101", "Alice Svensson", Department.DEVELOPMENT, 48000.0, 2019, Arrays.asList("Java", "Spring", "Docker")));
        employees.add(new Employee("E102", "Bob Berg", Department.DEVELOPMENT, 52000.0, 2017, Arrays.asList("Java", "Kotlin", "Kubernetes", "SQL")));
        employees.add(new Employee("E103", "Cecilia Lind", Department.DESIGN, 42000.0, 2021, Arrays.asList("UX", "Figma", "CSS")));
        employees.add(new Employee("E104", "David Ek", Department.MARKETING, 39000.0, 2020, Arrays.asList("SEO", "Copywriting", "Analytics")));
        employees.add(new Employee("E105", "Elena Rossi", Department.DEVELOPMENT, 45000.0, 2022, Arrays.asList("Java", "React", "TypeScript")));
        employees.add(new Employee("E106", "Fredrik Holm", Department.HR, 38000.0, 2018, Arrays.asList("Recruiting", "Payroll")));
        employees.add(new Employee("E107", "Gustav Vasa", Department.MANAGEMENT, 65000.0, 2015, Arrays.asList("Leadership", "Strategy", "Budgeting")));
        employees.add(new Employee("E108", "Hannah Abbott", Department.DESIGN, 44000.0, 2023, Arrays.asList("UI", "Figma", "Illustration")));
        return employees;
    }
}