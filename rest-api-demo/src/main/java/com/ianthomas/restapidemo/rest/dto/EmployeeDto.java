package com.ianthomas.restapidemo.rest.dto;

import com.ianthomas.restapidemo.persistence.model.Customer;
import com.ianthomas.restapidemo.persistence.model.Employee;
import com.sun.istack.NotNull;

import javax.persistence.Id;
import java.io.Serializable;

public class EmployeeDto implements Serializable {

    private Integer id;
    private String lastName;
    private String firstName;
    private String email;
    private Integer officeId;
    private String jobTitle;
    private Object object;

    public EmployeeDto(Integer id, String lastName, String firstName, String email, Integer officeId, String jobTitle) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.officeId = officeId;
        this.jobTitle = jobTitle;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Integer officeId) {
        this.officeId = officeId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Employee toEntity() {
        return new Employee(id, lastName, firstName, email, officeId, jobTitle);
    }
}
