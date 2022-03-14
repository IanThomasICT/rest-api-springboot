package com.ianthomas.restapidemo.service;

import com.ianthomas.restapidemo.exception.InvalidArgumentsException;
import com.ianthomas.restapidemo.exception.ItemAlreadyExistsException;
import com.ianthomas.restapidemo.exception.ItemNotFoundException;
import com.ianthomas.restapidemo.persistence.model.Employee;
import com.ianthomas.restapidemo.persistence.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }


    public Employee getEmployee(Integer id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent())
            return employee.get();
        else
            throw new ItemNotFoundException("Employee " + id + " not found");
    }

    public Employee addEmployee(Employee employee) {
        Boolean exists = employeeRepository.existsById(employee.getEmployeeId());
        if (exists) {
            throw new ItemAlreadyExistsException("Employee already exists");
        }
        employeeRepository.save(employee);
        return employee;
    }

    public void deleteEmployee(Integer id) {
        Boolean exists = employeeRepository.existsById(id);
        if (!exists) {
            throw new ItemNotFoundException("Employee does not exist");
        }
        employeeRepository.deleteById(id);
    }

    public List<Employee> queryEmployees(String jobTitle, Integer officeId) {
        if (jobTitle != null){
            LOG.info("Querying employees by job title");
            return employeeRepository.findByJobTitle(jobTitle);
        } else if (officeId != null){
            LOG.info("Querying employees by office id");
            return employeeRepository.findByOfficeId(officeId);
        }
        else {
            LOG.warn("Invalid employee query parameters");
            throw new InvalidArgumentsException("Invalid query parameters");
        }
    }
}
