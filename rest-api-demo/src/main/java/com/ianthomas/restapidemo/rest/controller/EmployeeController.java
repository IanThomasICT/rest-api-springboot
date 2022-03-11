package com.ianthomas.restapidemo.rest.controller;

import com.ianthomas.restapidemo.persistence.model.Employee;
import com.ianthomas.restapidemo.rest.dto.EmployeeDto;
import com.ianthomas.restapidemo.rest.dto.ResponseDto;
import com.ianthomas.restapidemo.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(produces = "application/json")
    public List<Employee> getEmployees() {
        return employeeService.getEmployees();
    }

    @GetMapping(path = "{id}", produces = "application/json")
    public Employee getEmployee(@PathVariable Integer id) {
        return employeeService.getEmployee(id);
    }

    @PostMapping(produces = {"application/json", "text/json"})
    public ResponseDto addEmployee(@RequestBody EmployeeDto customerDto) {
        return new ResponseDto("success", "Employee added successfully", employeeService.addEmployee(customerDto.toEntity()));
    }

    @DeleteMapping(path = "{id}")
    public ResponseDto deleteEmployee(@PathVariable Integer id) {
        employeeService.deleteEmployee(id);
        return new ResponseDto("success","Employee deleted successfully");
    }


}
