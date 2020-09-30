package dao;
import models.Department;
import models.News;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class Sql2oNewsDao {
    private final Sql2o sql2o;

    public Sql2oNewsDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(News news) {
        String sql = "INSERT INTO news(newsTitle, newsContent, departmentId) VALUES (:newsTitle, :newsContent, :departmentId)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .bind(news)
                    .executeUpdate()
                    .getKey();
            news.setId(id);

        } catch (Sql2oException ex) {
            System.out.println(ex);
        }

    }

    @Override
    public void addNewsToDepartment(News news, Department department) {
        String sql = "INSERT INTO departments_news (departmentId, newsId) VALUES (:departmentId, :newsId)";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("departmentId", department.getId())
                    .addParameter("newsId", news.getId())
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }

    }

    @Override
    public List<News> getAll() {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM news")
                    .executeAndFetch(News.class);
        }
    }

    @Override
    public News findById(int id) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM news WHERE id=:id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(News.class);
        }
    }

    @Override
    public List<News> getAllNewsByDepartment(int departmentId) {
        List<News> news = new ArrayList<>();
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM news WHERE departmentid = :departmentId")
                    .addParameter("departmentId", departmentId)
                    .executeAndFetch(News.class);
        }
    }

    @Override
    public List<News> getNewsByDepartment(int departmentId) {
        List<News> news = new ArrayList(); //empty list
        String joinQuery = "SELECT newsId FROM departments_news WHERE departmentId = :departmentId";

        try (Connection con = sql2o.open()) {
            List<Integer> allNewsIds = con.createQuery(joinQuery)
                    .addParameter("departmentId", departmentId)
                    .executeAndFetch(Integer.class);
            for (Integer newsId : allNewsIds){
                String usersQuery = "SELECT * FROM news WHERE id = :newsId";
                news.add(
                        con.createQuery(usersQuery)
                                .addParameter("newsId", newsId)
                                .executeAndFetchFirst(News.class));
            }
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
        return news;
    }

    @Override
    public void update(int id, String newNewsTitle, String newNewsContent, int departmentId) {
        String sql = "UPDATE news SET (newsTitle, newsContent, departmentId) = (:newsTitle, :newsContent, :departmentId) WHERE id=:id"; //CHECK!!!
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("newsTitle", newNewsTitle)
                    .addParameter("newsContent", newNewsContent)
                    .addParameter("departmentId", departmentId)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }

    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from news WHERE id = :id"; //raw sql
        String deleteJoin = "DELETE from departments_news WHERE departmentId = :departmentId";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
            con.createQuery(deleteJoin)
                    .addParameter("departmentId", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }

    }

    public void clearAll() {
        String sql = "DELETE FROM news";
        try(Connection con = sql2o.open()){
            con.createQuery(sql).executeUpdate();
        }catch (Sql2oException ex) {
            System.out.println(ex);
        }

    }




}
