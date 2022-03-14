package com.ianthomas.restapidemo.persistence.model;

import com.ianthomas.restapidemo.exception.InvalidArgumentsException;
import com.ianthomas.restapidemo.persistence.annotation.Indexable;
import com.sun.istack.NotNull;
import org.elasticsearch.common.xcontent.XContentBuilder;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.IOException;
import java.util.Objects;

@Entity
@Indexable
public class Employee extends PersistentEntity{
    @Id private Integer employeeId;
    @NotNull private String lastName;
    @NotNull private String firstName;
    @NotNull private String email;
    @NotNull private Integer officeId;
    @NotNull private String jobTitle;

    public Employee(Integer employeeId, String lastName, String firstName, String email, Integer officeId, String jobTitle) {
        try {
            Objects.requireNonNull(employeeId, "Invalid employeeId: null value");
            Objects.requireNonNull(firstName, "Invalid first name: null value");
            Objects.requireNonNull(lastName, "Invalid last name: null value");
            Objects.requireNonNull(email, "Invalid email: null value");
            Objects.requireNonNull(officeId, "Invalid office employeeId: city cannot be null");
            Objects.requireNonNull(jobTitle, "Invalid job title: null value");
         } catch (NullPointerException e) {
            throw new InvalidArgumentsException(e.getMessage(), e);
        }
        this.employeeId = employeeId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.officeId = officeId;
        this.jobTitle = jobTitle;
    }

    public Employee() {
    }


    @Override
    public XContentBuilder updateBuilderFields(XContentBuilder builder) throws IOException {
        builder = super.updateBuilderFields(builder)
                .field("employeeID", employeeId)
                .field("firstName",firstName)
                .field("lastName",lastName)
                .field("email",email)
                .field("officeId",officeId)
                .field("jobTitle",jobTitle);
        return builder;
    }


    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
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
}
