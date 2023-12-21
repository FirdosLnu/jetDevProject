package com.jetdev.app.repository;


import com.jetdev.app.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    Employee getByName(String name);

    Employee findByName(String name);
}
