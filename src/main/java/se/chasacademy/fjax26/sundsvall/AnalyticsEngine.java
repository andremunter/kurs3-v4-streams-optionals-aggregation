package se.chasacademy.fjax26.sundsvall;

import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AnalyticsEngine {

    public static List<Employee> getTopEarnersInDepartment(List<Employee> employees, Department dept, int n) {
        return employees.stream()
                .filter(e -> e.getDepartment() == dept)
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .limit(n)
                .toList();
    }

    public static double getTotalSalaryBudget(List<Employee> employees, Department dept) {
        return employees.stream()
                .filter(e -> e.getDepartment() == dept)
                .mapToDouble(Employee::getSalary)
                .sum();
    }

    public static String generateDepartmentReport(List<Employee> employees) {
        Map<Department, DoubleSummaryStatistics> statsByDept = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.summarizingDouble(Employee::getSalary))
                );

        return statsByDept.entrySet().stream()
                .map(e -> String.format(
                        "%-12s | #Employees: %-3d | Average salary: %-10.2f",
                        e.getKey(), e.getValue().getCount(), e.getValue().getAverage()
                ))
                .collect(Collectors.joining("\n"));
    }

}
