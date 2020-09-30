package dao;
import models.Department;
import models.News;
import org.junit.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import java.util.Arrays;
import static org.junit.Assert.assertEquals;


public class Sql2oNewsDaoTest {

    private static Connection conn;
    private static Sql2oDepartmentDao departmentsDao;
    private static Sql2oNewsDao newsDao;
    private static Sql2oUserDao usersDao;

    @BeforeClass
    public static void setUp() throws Exception {
//        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
//        Sql2o sql2o = new Sql2o(connectionString, "", "");
        String connectionString = "jdbc:postgresql://localhost:5432/myorg_test";
        Sql2o sql2o = new Sql2o(connectionString, "moringa", "Jkg25879");
        departmentsDao = new Sql2oDepartmentDao(sql2o);
        newsDao = new Sql2oNewsDao(sql2o);
        usersDao = new Sql2oUserDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("clearing database");
        departmentsDao.clearAll();
        newsDao.clearAll();
        usersDao.clearAll();
//        conn.close();
    }

    @AfterClass     //changed to @AfterClass (run once after all tests in this file completed)
    public static void shutDown() throws Exception{ //changed to static
        conn.close(); // close connection once after this entire test file is finished
        System.out.println("connection closed");
    }

    //helper
    public News setUpNews(){
        News testNews = new News("Money", "The finances are crazy");
        newsDao.add(testNews);
        return testNews;
    }
    //helper
    public News setUpAltNews(){
        News altTestNews = new News("Terms", "Conditions", 2);
        newsDao.add(altTestNews);
        return altTestNews;
    }

    @Test
    public void returnsTheIdOfNewsInstanceCorrectly(){
        News testNews = setUpNews();
        assertEquals(testNews.getId(), testNews.getId());
    }

    @Test
    public void news_instantiatesCorrectly_true(){
        News testNews = setUpNews();
        assertEquals(true, testNews instanceof News);
    }

    @Test
    public void returnsTheCorrectTotalInstancesOfTheClass(){
        News testNews = setUpNews();
        assertEquals(1, newsDao.getAll().size());
    }

    @Test
    public void returnsZeroIfNoInstanceOfNewsExists(){
        assertEquals(0, newsDao.getAll().size());
    }

    @Test
    public void findByIdReturnsCorrectNewsInstance(){
        News testNews = setUpNews();
        newsDao.add(testNews);
        News alternateNews = setUpAltNews();
        newsDao.add(alternateNews);
        assertEquals(testNews, newsDao.findById(testNews.getId()));
    }

    @Test
    public void update_UpdatesCorrectlyUpdatesAllFieldCorrectly_true(){
        News testAltNews = setUpAltNews();
        newsDao.update(testAltNews.getId(), "mango", "farming is fine", 12);
        News foundNews = newsDao.findById(testAltNews.getId());
        assertEquals("mango", foundNews.getNewsTitle());
        assertEquals("farming is fine", foundNews.getNewsContent());
        assertEquals(12, foundNews.getDepartmentId());
    }


    @Test
    public void deletesTheRightNewsByIdCorrectly(){
        News testNews = setUpNews();
        newsDao.deleteById(testNews.getId());
        assertEquals(0, newsDao.getAll().size());
    }

    @Test
    public void clearsAllNewsAfterClearAllMethodIsCalled(){
        News firstNews = setUpNews();
        News anotherNews = setUpAltNews();
        newsDao.clearAll();
        assertEquals(0, newsDao.getAll().size());
    }

    @Test
    public void deletingADepartmentAlsoUpdatesTheNewDepartmentJointTable(){
        Department testDepartment = new Department("Finance", "Money", 1232);
        departmentsDao.add(testDepartment);
        Department altTestDepartment = new Department("Food", "catering", 100);
        departmentsDao.add(altTestDepartment);

        News testNews = setUpNews();
        newsDao.add(testNews);

        newsDao.addNewsToDepartment(testNews, testDepartment);
        newsDao.addNewsToDepartment(testNews, altTestDepartment);

        newsDao.deleteById(testDepartment.getId());
        assertEquals(0, newsDao.getNewsByDepartment(testDepartment.getId()).size());

    }

}