# Spring Boot REST API Demo
## [VERSION 2]

#### Models:
    - Customers
    - Payments
    - Employees
    - Office

### Schema:
Customers {
    int id (PK)
    string companyName
    string contactFirstName
    string contactLastName
    string phone
    string addressLine1
    string addressLine2
    string city
    string state
    string postcalCode
}

Payments {
    int customerId (FK)
    int employeeId (FK)
    date paymentDate
    double amount
}

Employees {
    int id (PK)
    string lastName
    string firstName
    string email
    int officeId (FK)
    string jobTitle
}

Offices {
    int id (PK)
    string phone
    string addressLine1
    string addressLine2
    string city
    string state
    string postalCode
}