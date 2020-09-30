import org.junit.Test;
import static org.junit.Assert.*;

public class AppTest {

    @Test
    public void creates_instanceOfApp_true(){
        App newApp = new App();
        assertTrue(newApp instanceof App);
    }

}