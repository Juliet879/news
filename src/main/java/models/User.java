package models;

import java.util.Objects;

public class User {

    private String name;
    private String companyPosition;
    private String role;
    private  int departmentId;
    private int id;

    //User Constructor
    public User(String name, String companyPosition, String role, int departmentId){
        this.name = name;
        this.companyPosition = companyPosition;
        this.role = role;
        this.departmentId = departmentId;

    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getCompanyPosition() {
        return companyPosition;
    }

    public void setCompanyPosition(String companyPosition) {
        this.companyPosition = companyPosition;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getDepartmentId() == user.getDepartmentId() &&
                getId() == user.getId() &&
                Objects.equals(getName(), user.getName()) &&
                Objects.equals(getCompanyPosition(), user.getCompanyPosition()) &&
                Objects.equals(getRole(), user.getRole());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getCompanyPosition(), getRole(), getDepartmentId(), getId());
    }


}
