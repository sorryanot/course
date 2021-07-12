package addressbook;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class GroupCreationTests {
    private WebDriver wd;

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
        wd = new ChromeDriver();
        wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        wd.get("http://localhost/addressbook/");
        wd.findElement(By.name("user")).sendKeys("admin");
        wd.findElement(By.name("pass")).sendKeys("secret");
        wd.findElement(By.xpath("//input[@value='LOGIN']")).click();
    }

    @Test
    public void testGroupCreation() throws Exception {

        wd.findElement(By.linkText("GROUPS")).click();
        wd.findElement(By.name("new")).click();
        wd.findElement(By.name("group_name")).sendKeys("test1");
        wd.findElement(By.name("group_header")).sendKeys("test2");
        wd.findElement(By.name("group_footer")).sendKeys("test3");
        wd.findElement(By.xpath("//input[@value='Enter information']")).click();
        wd.findElement(By.linkText("group page")).click();
        wd.findElement(By.linkText("LOGOUT")).click();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception {
        wd.quit();
    }

    public static boolean isAlertPresent(ChromeDriver wd) {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
}


