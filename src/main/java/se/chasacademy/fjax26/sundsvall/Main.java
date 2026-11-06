package se.chasacademy.fjax26.sundsvall;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

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

        IO.println("\n1B:");
        IO.println("Employee names in uppercase:");
        List<String> employeeNameUpperCase = Main.getEmployeeNamesInUpperCase(employees);
        employeeNameUpperCase.forEach(IO::println);

        IO.println("\n1C:");
        IO.println("3 highest paid employees:");
        List<Employee> top3paid = Main.getTop3HighestPaid(employees);
        top3paid.forEach(IO::println);

        IO.println("\n====================");

        Employee defaultEmployee = new Employee("E000", "DEFAULT EMPLOYEE", Department.DEVELOPMENT, 0.0, 0, List.of());

        IO.println("\n2A:");
        IO.println("Find employee by id:");
        Employee foundEmployeeById = Main.findEmployeeById(employees, "E106")               .orElse(defaultEmployee);
        Employee notFoundEmployeeById = Main.findEmployeeById(employees, "Non-Existent")    .orElse(defaultEmployee);
        IO.println(foundEmployeeById);
        IO.println(notFoundEmployeeById);

        IO.println("\n2B:");
        IO.println("Highest paid in department:");
        Employee highestPaidInDevelopment = Main.findHighestPaidInDepartment(employees, Department.DEVELOPMENT) .orElse(defaultEmployee);
        Employee highestPaidInDesign = Main.findHighestPaidInDepartment(employees, Department.DESIGN)           .orElse(defaultEmployee);
        IO.println(highestPaidInDevelopment);
        IO.println(highestPaidInDesign);

        IO.println("\n2C:");
        IO.println("Employee name or fallback:");
        String employeeNameValid = Main.getEmployeeNameOrFallback(employees, "E106");
        String employeeNameInvalid = Main.getEmployeeNameOrFallback(employees, "Non-Existent");
        IO.println(employeeNameValid);
        IO.println(employeeNameInvalid);
    }

    // =========== ====== ===========
    // =========== SPÅR 1 ===========
    // =========== ====== ===========

    // Övning 1 (Insteg) – Grundläggande Pipelines (filter, map, sorted, toList)
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

    // Övning 2 (Insteg/Medium) – Kraschfri sökning med Optional<T>
    // 2A
    public static Optional<Employee> findEmployeeById(List<Employee> employees, String id) {
        return employees.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst();
    }

    // 2B
    public static Optional<Employee> findHighestPaidInDepartment(List<Employee> employees, Department dept) {
        return employees.stream()
                .filter(e -> e.getDepartment() == dept)
                .max(Comparator.comparing(Employee::getSalary));
    }

    // 2C
    public static String getEmployeeNameOrFallback(List<Employee> employees, String id) {
        return employees.stream()
                .filter(e -> e.getId().equals(id))
                .map(Employee::getName)
                .findFirst()
                .orElse("Okänd anställd");
    }


}
