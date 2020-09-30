package dao;
import models.*;
import java.util.List;

public interface NewsDao {

    //create
    void add(News news);
    void addNewsToDepartment(News news, Department department);

    //read
    List<News> getAll();
    News findById(int id);
    List<News> getAllNewsByDepartment(int departmentId);
    //
    List<News> getNewsByDepartment(int departmentId);
    //

    //update
    void update(int id, String newsTitle, String newsContent,int departmentId);

    //delete
    void deleteById(int id);
    void clearAll();

}
