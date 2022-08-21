package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.List;

@RestController
public class EmployeeController {

    EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService/*, @Qualifier("restTemplate1") RestTemplate restTemplate*/) {
        this.employeeService = employeeService;
//        System.out.println(restTemplate.hashCode());
    }

    @GetMapping("/employee")
    public Employee getEmployee(@RequestParam String id) {
        return employeeService.getEmployee(id);
    }

    @GetMapping("/employees")
    public List<Employee> getEmployees() {
        return employeeService.getAllEmployees();
    }

    @PatchMapping("/employee")
    public Employee updateEmployee(@RequestParam String id, @RequestBody Employee employee) {
        return employeeService.updateEmployee(id, employee);
    }

    @PostMapping("/add/employee")
    public String addEmployee(@Valid @RequestBody Employee employee) {
        employeeService.addEmployee(employee);
        return "Success";
    }

    @GetMapping("/employees/greater/{age}")
    public List<Employee> getEmployeesWithAgeGreaterThan(@PathVariable int age) {
        return employeeService.getEmployeesWithAgeGreaterThan(age);
    }

    @GetMapping("/employees/younger/{age}")
    public List<Employee> getEmployeesWithAgeYoungerThan(@PathVariable int age) {
        return employeeService.getEmployeesWithAgeLesserThan(age);
    }
}
