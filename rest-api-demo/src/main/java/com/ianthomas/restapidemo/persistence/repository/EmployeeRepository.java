package com.ianthomas.restapidemo.persistence.repository;

import com.ianthomas.restapidemo.persistence.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    List<Employee> findByJobTitle(String jobTitle);
    List<Employee> findByOfficeId(Integer officeId);
}
