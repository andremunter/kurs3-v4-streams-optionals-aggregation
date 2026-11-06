package se.chasacademy.fjax26.sundsvall;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Employee {
    private final String id;
    private final String name;
    private final Department department;
    private final double salary;
    private final int hireYear;
    private final List<String> skills;

    public Employee(String id, String name, Department department, double salary, int hireYear, List<String> skills) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
        this.hireYear = hireYear;
        this.skills = new ArrayList<>(skills);
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public Department getDepartment() { return department; }
    public double getSalary() { return salary; }
    public int getHireYear() { return hireYear; }
    public List<String> getSkills() { return new ArrayList<>(skills); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("%-6s | %-16s | %-12s | %8.2f kr | Anställd: %d | Skills: %s",
                id, name, department, salary, hireYear, String.join(", ", skills));
    }
}