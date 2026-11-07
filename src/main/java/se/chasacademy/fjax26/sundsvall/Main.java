package se.chasacademy.fjax26.sundsvall;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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

        IO.println("\n====================");

        IO.println("\n3A:");
        IO.println("Senior Employee (Pay over 60k):");
        boolean anySeniorEmployee = Main.hasSeniorManagement(employees);
        IO.println("Senior employees? " + anySeniorEmployee);

        IO.println("\n3B:");
        IO.println("All employees earn above n?");
        boolean allEarnAbove50k = Main.allEarnAbove(employees, 50_000);
        boolean allEarnAbove10k = Main.allEarnAbove(employees, 10_000);
        IO.println("Above 50k? " + allEarnAbove50k);
        IO.println("Above 10k? " + allEarnAbove10k);

        IO.println("\n3C:");
        IO.println("Unique departments from Employee list");
        List<Department> uniqueDept = Main.getActiveDepartments(employees);
        IO.println(uniqueDept);

        IO.println("""
                
                ===== ====== =====
                ===== SPÅR 2 =====
                ===== ====== =====""");

        IO.println("\n4A:");
        IO.println("Group employees by department:");
        Map<Department, List<Employee>> employeesByDepartment = Main.groupEmployeesByDepartment(employees);
        employeesByDepartment.forEach(
                (dept, empList) -> {
                    IO.println(dept + ":");
                    empList.forEach(e -> IO.println("   " + e));
                }
        );

        IO.println("\n4B:");
        IO.println("Partition by salary:");

        IO.println();

        IO.println("Above 50k?");
        Map<Boolean, List<Employee>> partitionedBySalary50k = Main.partitionByHighSalary(employees, 50_000);
        partitionedBySalary50k.forEach(
                (part, empList) -> {
                    IO.println(part + ":");
                    empList.forEach(e -> IO.println("   " + e));
                }
        );

        IO.println();

        IO.println("Above 40k?");
        Map<Boolean, List<Employee>> partitionedBySalary40k = Main.partitionByHighSalary(employees, 40_000);
        partitionedBySalary40k.forEach(
                (part, empList) -> {
                    IO.println(part + ":");
                    empList.forEach(e -> IO.println("   " + e));
                }
        );

        IO.println("\n====================");

        IO.println("\n5A:");
        IO.println("Average salary per department:");
        Map<Department, Double> departmentAvgSalary = Main.getAverageSalaryPerDepartment(employees);
        departmentAvgSalary.forEach(
                (dept, avg) -> IO.println(
                        String.format("%-12s | %.2f", dept, avg)
                )
        );

        IO.println("\n5B:");
        IO.println("Average salary per department:");
        Map<Department, Long> employeesPerDept = Main.getEmployeeCountPerDepartment(employees);
        IO.println(employeesPerDept);

        IO.println("\n5C:");
        IO.println("List of employee names per department");
        Map<Department, String> namesAtDept = Main.getEmployeeNamesPerDepartment(employees);
        namesAtDept.forEach(
                (dept, names) -> IO.println(
                        String.format("%-12s | %s", dept, names)
                )
        );

        IO.println("\n====================");

        IO.println("\n6A:");
        IO.println("List of unique skills in alphabetical order:");
        List<String> uniqueSkills = Main.getAllUniqueSkills(employees);
        IO.println(uniqueSkills);

        IO.println("\n6B:");
        IO.println("The most common skill:");
        Optional<String> mostCommonSkill = Main.getMostCommonSkill(employees);
        IO.println(mostCommonSkill.orElse("No skills found"));

        IO.println("""
                
                ===== ====== =====
                ===== SPÅR 3 =====
                ===== ====== =====""");

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

    // Övning 3 (Medium) – Tillståndskontroller & Unika Samlingar
    // 3A
    public static boolean hasSeniorManagement(List<Employee> employees) {
        return employees.stream()
                .anyMatch(e -> e.getSalary() > 60_000);
    }

    // 3B
    public static boolean allEarnAbove(List<Employee> employees, double minSalary) {
        return employees.stream()
                .allMatch(e -> e.getSalary() > minSalary);
    }

    // 3C
    public static List<Department> getActiveDepartments(List<Employee> employees) {
        return employees.stream()
                .map(Employee::getDepartment)
                .distinct()
                .toList();
    }

    // =========== ====== ===========
    // =========== SPÅR 2 ===========
    // =========== ====== ===========

    //Övning 4 (Medium) – Datagruppering med Collectors.groupingBy() & partitioningBy()
    // 4A
    public static Map<Department, List<Employee>> groupEmployeesByDepartment(List<Employee> employees) {
        return employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }

    // 4B
    public static Map<Boolean, List<Employee>> partitionByHighSalary(List<Employee> employees, double threshold) {
        return employees.stream()
                .collect(Collectors.partitioningBy(e -> e.getSalary() > threshold));
    }

    // Övning 5 (Avancerad) – Downstream Collectors & Statistiska Sammanställningar
    // 5A
    public static Map<Department, Double> getAverageSalaryPerDepartment(List<Employee> employees) {
        return employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)));
    }

    // 5B
    public static Map<Department, Long> getEmployeeCountPerDepartment(List<Employee> employees) {
        return employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
    }

    // 5C
    public static Map<Department, String> getEmployeeNamesPerDepartment(List<Employee> employees) {
        return employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.mapping(Employee::getName, Collectors.joining(", "))));
    }

    // Övning 6 (Avancerad) – Platta ut nästlade samlingar (flatMap)
    // 6A
    public static List<String> getAllUniqueSkills(List<Employee> employees) {
        return employees.stream()
                .flatMap(e -> e.getSkills().stream())
                .distinct()
                .sorted(String::compareTo)
                .toList();
    }

    // 6B
    public static Optional<String> getMostCommonSkill(List<Employee> employees) {
        return employees.stream()
                .flatMap(e -> e.getSkills().stream())
                .collect(Collectors.groupingBy(skill -> skill, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);
    }

    // =========== ====== ===========
    // =========== SPÅR 3 ===========
    // =========== ====== ===========
}
