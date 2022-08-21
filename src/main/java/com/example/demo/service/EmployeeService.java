package com.example.demo.service;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.List;

//@Service
public class EmployeeService {

    EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository/*, @Qualifier("restTemplate2") RestTemplate restTemplate*/) {
//        System.out.println(restTemplate.hashCode());
        this.employeeRepository = employeeRepository;
    }

    public void addEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Transactional
    public Employee updateEmployee(String id, Employee employee) {
        Employee employee1 = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        if(employee.getAge() != 0) employee1.setAge(employee.getAge());
        if(employee.getPhone() != null) employee1.setPhone(employee.getPhone());
        employeeRepository.save(employee1);
        doSomething();
        return employee1;
    }

    private void doSomething() {
        throw new RuntimeException("Update should fail");
    }

    public List<Employee> getEmployeesWithAgeGreaterThan(int age) {
        return employeeRepository.findAllByAgeGreaterThan(age);
    }

    public List<Employee> getEmployeesWithAgeLesserThan(int age) {
        return employeeRepository.findAllEmployeesAgeLesserThan(age);
    }

    public void deleteEmployee(String id) {
        if (!employeeRepository.existsById(id)) {
            throw new RuntimeException("Employee not found");
        }
        employeeRepository.deleteById(id);
    }
    public Employee getEmployee(String employeeId) {
        return employeeRepository.findById(employeeId).orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
}
