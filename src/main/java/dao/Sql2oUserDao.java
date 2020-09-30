package dao;
import models.News;
import models.User;

import java.util.ArrayList;
import java.util.List;
import org.sql2o.Sql2o;
import org.sql2o.Connection;
import org.sql2o.Sql2oException;

public class Sql2oUserDao implements UserDao{

    private final Sql2o sql2o;

    public Sql2oUserDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(User user) {
        String sql = "INSERT INTO users (userName, userCompanyPosition, userCompanyRole, departmentId) VALUES (:userName, :userCompanyPosition, :userCompanyRole, :departmentId)";
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql, true)
                    .bind(user)
                    .executeUpdate()
                    .getKey();
            user.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<User> getAllUsers() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM users")
                    .executeAndFetch(User.class);
        }
    }

    @Override
    public User findUserById(int id) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM users WHERE id=:id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(User.class);
        }
    }

    @Override
    public List<User> getAllUsersByDepartment(int departmentId) {
        List<News> news = new ArrayList<>();
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM users WHERE departmentid = :departmentId")
                    .addParameter("departmentId", departmentId)
                    .executeAndFetch(User.class);
        }
    }

    @Override
    public void update(int id, String newUserName, String newUserCompanyPosition, String newUserCompanyRole, int departmentId) {
        String sql = "UPDATE users SET (userName, userCompanyPosition, userCompanyRole, departmentId) = (:userName, :userCompanyPosition, :userCompanyRole, :departmentId) WHERE id=:id"; //CHECK!!!
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("userName", newUserName)
                    .addParameter("userCompanyPosition", newUserCompanyPosition)
                    .addParameter("userCompanyRole", newUserCompanyRole)
                    .addParameter("departmentId", departmentId)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }

    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from users WHERE id = :id"; //raw sql
        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }catch (Sql2oException ex){
            System.out.println(ex);
        }

    }

    @Override
    public void clearAll() {
        String sql = "DELETE FROM users";
        try(Connection con = sql2o.open()){
            con.createQuery(sql).executeUpdate();
        }catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }


}