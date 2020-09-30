package dao;
import models.*;
import java.util.List;

public interface UserDao {

    //create
    void add (User user);

    //read
    List<User> getAllUsers();
    User findUserById(int id);
    List<User> getAllUsersByDepartment(int departmentId);

    //update
    void update(int id, String userName,  String userCompanyPosition, String useCompanyRole, int departmentId);

    //delete
    void deleteById(int id);
    void clearAll();

}