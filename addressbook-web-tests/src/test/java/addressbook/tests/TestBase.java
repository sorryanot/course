package addressbook.tests;

import addressbook.appmanager.ApplicationManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBase {

    public final ApplicationManager app = new ApplicationManager();

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        app.init();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        app.stop();
    }

}