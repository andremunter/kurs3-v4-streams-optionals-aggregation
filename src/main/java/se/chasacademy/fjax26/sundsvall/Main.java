package se.chasacademy.fjax26.sundsvall;

import java.util.List;

public class Main {
    static void main() {

        List<Employee> employees = EmployeeTestData.getEmployees();

        IO.println("""
                ===== ====== =====
                ===== SPÅR 1 =====
                ===== ====== =====
                """);
        IO.println("1A:");
        IO.println("Developers:");
        List<Employee> developers = Main.getDevelopers(employees);
        developers.forEach(IO::println);

        IO.println("\n2A:");
        IO.println("Employee names in uppercase:");
        List<String> employeeNameUpperCase = Main.getEmployeeNamesInUpperCase(employees);
        employeeNameUpperCase.forEach(IO::println);

        IO.println("\n3A:");
        IO.println("3 highest paid employees:");
        List<Employee> top3paid = Main.getTop3HighestPaid(employees);
        top3paid.forEach(IO::println);

    }

    // =========== ====== ===========
    // =========== SPÅR 1 ===========
    // =========== ====== ===========

    // 1A:
    public static List<Employee> getDevelopers(List<Employee> employees) {
        return employees.stream()
                .filter(e -> e.getDepartment() == Department.DEVELOPMENT)
                .toList();
    }

    // 1B:
    public static List<String> getEmployeeNamesInUpperCase(List<Employee> employees) {
        return employees.stream()
                .map(e -> e.getName().toUpperCase())
                .toList();
    }

    // 1C:
    public static List<Employee> getTop3HighestPaid(List<Employee> employees) {
        return employees.stream()
                .sorted((e1, e2) -> Double.compare(e2.getSalary(), e1.getSalary()))
                .limit(3)
                .toList();
    }
}
