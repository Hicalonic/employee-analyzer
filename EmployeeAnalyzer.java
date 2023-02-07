package academy.mindswap;

import academy.mindswap.employee.Employee;

import java.time.Year;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class EmployeeAnalyzer {

    public long countEmployees(List<Employee> employees, int years) {
        return employees.stream()
                .filter(employee -> (Year.now().getValue() - employee.getStartingYear()) >= years)
                .count();
    }

    public List<String> findEmployeeBySalary(List<Employee> employees, int salary) {
        return employees.stream().filter(employee -> employee.getSalary() > salary)
                .map(employee -> employee.getFirstName() + " " + employee.getLastname()).toList();
    }

    public List<Employee> findOldestEmployees(List<Employee> employees, int numberOfEmployees) {
        /*return employees.stream().max(new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return o1.getAge() - o2.getAge();
            }
        })*/
        //return employees.stream().max(Comparator.comparing(Employee::getAge))
        return employees.stream().sorted(Comparator.comparing(Employee::getAge).reversed()).limit(numberOfEmployees).toList();
    }

    public Optional<Employee> findFirstEmployeeByAge(List<Employee> employees, int age) {
        return employees.stream().filter(employee -> employee.getAge() >= age).findFirst();
    }

    public Double findAverageSalary(List<Employee> employees) {
        return Math.floor(employees.stream().mapToInt(employee -> employee.getSalary()).average().getAsDouble()*100) / 100;
    }

    public List<String> findCommonNames(List<Employee> firstDepartment, List<Employee> secondDepartment) {

        List<String> lista1 = firstDepartment.stream().map(employee -> employee.getFirstName()).toList();
        List<String> lista2 = secondDepartment.stream().map(employee -> employee.getFirstName()).toList();

        //return lista1.stream().filter(list2 -> list2.contains(lista1.toString())).distinct().collect(Collectors.toList());
        return lista1.stream().filter(lista2::contains).distinct().collect(Collectors.toList());

    }
}
