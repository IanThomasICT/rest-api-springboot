package com.ianthomas.restapidemo.persistence.model;

import com.ianthomas.restapidemo.exception.InvalidArgumentsException;
import com.sun.istack.NotNull;
import org.elasticsearch.common.xcontent.XContentBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.IOException;
import java.util.Objects;

@Entity
public class Customer extends PersistentEntity{

    @Id private Integer id;
    @NotNull private String companyName;
    @NotNull private String contactFirstName;
    @NotNull private String contactLastName;
    @NotNull private String addressLine1;
    @NotNull private String city;
    @NotNull @Column(length = 15) private String postalCode;
    @NotNull private String country;

    private String phone;
    private String addressLine2;
    private String state;

    public Customer(Integer id, String companyName, String contactFirstName, String contactLastName, String phone, String addressLine1, String addressLine2, String city, String state, String postalCode, String country) {

        try {
            Objects.requireNonNull(id, "Invalid id: null value");
            Objects.requireNonNull(companyName, "Invalid companyName: null value");
            Objects.requireNonNull(contactFirstName, "Invalid first name: null value");
            Objects.requireNonNull(contactLastName, "Invalid last name: null value");
            Objects.requireNonNull(addressLine1, "Invalid address: null value");
            Objects.requireNonNull(city, "Invalid city: city cannot be null");
            Objects.requireNonNull(postalCode, "Invalid postalCode: null value");
            Objects.requireNonNull(country, "Invalid country: null value");
        } catch (NullPointerException e) {
            throw new InvalidArgumentsException(e.getMessage(), e);
        }
        this.id = id;
        this.companyName = companyName;
        this.contactFirstName = contactFirstName;
        this.contactLastName = contactLastName;
        this.phone = phone;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.country = country;
    }

    public Customer() {

    }


    @Override
    public XContentBuilder updateBuilderFields(XContentBuilder builder) throws IOException {
        builder = super.updateBuilderFields(builder)
                .field("customerId",id)
                .field("companyName",companyName)
                .field("contactFirstName",contactFirstName)
                .field("contactLastName",contactLastName)
                .field("phone",phone)
                .field("addressLine1",addressLine1)
                .field("addressLine2",addressLine2)
                .field("city",city)
                .field("state",state)
                .field("postalCode",postalCode)
                .field("country",country);
        return builder;
    }




    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContactFirstName() {
        return contactFirstName;
    }

    public void setContactFirstName(String contactFirstName) {
        this.contactFirstName = contactFirstName;
    }

    public String getContactLastName() {
        return contactLastName;
    }

    public void setContactLastName(String contactLastName) {
        this.contactLastName = contactLastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
