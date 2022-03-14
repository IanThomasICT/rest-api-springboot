package com.ianthomas.restapidemo.rest.controller;

import com.ianthomas.restapidemo.persistence.model.Employee;
import com.ianthomas.restapidemo.rest.dto.EmployeeDto;
import com.ianthomas.restapidemo.rest.dto.ResponseDto;
import com.ianthomas.restapidemo.service.EmployeeService;
import com.ianthomas.restapidemo.service.SearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final SearchService searchService;
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    public EmployeeController(EmployeeService employeeService, SearchService searchService) {
        this.employeeService = employeeService;
        this.searchService = searchService;
    }

    @GetMapping(produces = "application/json")
    public List<Employee> getEmployees() {
        return employeeService.getEmployees();
    }

    @GetMapping(path = "{id}", produces = "application/json")
    public Employee getEmployee(@PathVariable Integer id) {
        return employeeService.getEmployee(id);
    }

    @GetMapping(path = "/query", produces = "application/json")
    public List<Employee> queryEmployees(@RequestParam(required = false) String jobTitle,
                                         @RequestParam(required = false) Integer officeId){
        return employeeService.queryEmployees(jobTitle, officeId);
    }

    @GetMapping(path = "/match", produces = "application/json")
    public ResponseDto queryMatchEmployees(@RequestParam(required = true) String index,
                                           @RequestParam(required = true) String field,
                                           @RequestParam(required = true) String matchParam
                                              ){

        return new ResponseDto("success", "Displaying top hits for match query", searchService.queryMatchEmployees(index, field, matchParam));
    }

    @PostMapping(produces = {"application/json", "text/json"})
    public ResponseDto addEmployee(@RequestBody EmployeeDto employeeDto) {
        return new ResponseDto("success", "Employee added successfully", employeeService.addEmployee(employeeDto.toEntity()));
    }

    @DeleteMapping(path = "{id}")
    public ResponseDto deleteEmployee(@PathVariable Integer id) {
        employeeService.deleteEmployee(id);
        return new ResponseDto("success","Employee deleted successfully");
    }
}
