package com.example.demo.repository;

import com.example.demo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {

    List<Employee> findAllByAgeGreaterThan(int age);

    @Query(value = "select * from Employee where age < ?1", nativeQuery = true)
    List<Employee> findAllEmployeesAgeLesserThan(int age);
}
