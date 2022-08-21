package com.example.demo.service;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

public class EmployeeServiceTest {

    EmployeeService employeeService;
    EmployeeRepository employeeRepository;

    @BeforeEach
    void setUp() {
        employeeRepository = Mockito.mock(EmployeeRepository.class);
        employeeService = new EmployeeService(employeeRepository);
    }

    @Test
    void testUpdateEmployee_employeeDoesNotExists() {
        Mockito.when(employeeRepository.findById("1")).thenReturn(Optional.empty());
        RuntimeException runtimeException = Assertions.assertThrows(RuntimeException.class, ()->
        employeeService.updateEmployee("1", new Employee()));
        Assertions.assertEquals("Employee not found", runtimeException.getMessage());
    }

    @Test
    void testUpdateEmployee_employeeExists_updateAgeAndPhone() {
        Employee employee = new Employee("1", "employee", 20, "1231231231");
        Mockito.when(employeeRepository.findById("1")).thenReturn(Optional.of(new Employee("1", "employee", 25, "123")));
        Employee updatedEmployee = employeeService.updateEmployee("1", employee);
        Assertions.assertEquals(20, updatedEmployee.getAge());
        Assertions.assertEquals("1231231231", updatedEmployee.getPhone());
    }
}
