package models;

import java.util.Objects;

public class Department {
    private String name;
    private String description;
    private int employeesNumber;
    private int id;

    //Department Constructor
    public Department(String name, String description, int employeesNumber){
        this.name = name;
        this.description = description;
        this.employeesNumber = employeesNumber;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return employeesNumber == that.employeesNumber &&
                id == that.id &&
                name.equals(that.name) &&
                description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, employeesNumber, id);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getEmployeesNumber() {
        return employeesNumber;
    }

    public void setEmployeesNumber(int employeesNumber) {
        this.employeesNumber = employeesNumber;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
