package addressbook;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public class TestBase {
    public WebDriver wd;

    public static boolean isAlertPresent(ChromeDriver wd) {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
        wd = new ChromeDriver();
        wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        wd.get("http://localhost/addressbook/");
        login("admin", "secret");
    }

    public void login(String username, String password) {
        wd.findElement(By.name("user")).sendKeys(username);
        wd.findElement(By.name("pass")).sendKeys(password);
        wd.findElement(By.xpath("//input[@value='LOGIN']")).click();
    }

    public void returnToHomePage() {
        wd.findElement(By.linkText("HOME")).click();
    }

    public void submitSingleForm() {
        wd.findElement(By.name("submit")).click();
    }

    public void fillSingleForm(SimpleData simpleData) {
        wd.findElement(By.name("firstname")).sendKeys(simpleData.getFirstName());
        wd.findElement(By.name("lastname")).sendKeys(simpleData.getLastName());
        wd.findElement(By.name("email")).sendKeys(simpleData.getEmail());
    }

    public void goToaddNewContact() {
        wd.findElement(By.linkText("ADD_NEW")).click();
    }

    public void logout() {
        wd.findElement(By.linkText("LOGOUT")).click();
    }

    public void returnToGroupPage() {
        wd.findElement(By.linkText("group page")).click();
    }

    public void submitGroupCreation() {
        wd.findElement(By.xpath("//input[@value='Enter information']")).click();
    }

    public void fillGroupForm(GroupData groupData) {
        wd.findElement(By.name("group_name")).sendKeys(groupData.getGroupName());
        wd.findElement(By.name("group_header")).sendKeys(groupData.getGroupHeader());
        wd.findElement(By.name("group_footer")).sendKeys(groupData.getGroupFooter());
    }

    public void initGroupCreation() {
        wd.findElement(By.name("new")).click();
    }

    public void goToGroupPage() {
        wd.findElement(By.linkText("GROUPS")).click();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception {
        wd.quit();
    }

    protected void deleteSelectedGroup() {
        wd.findElement(By.name("delete")).click();
    }

    protected void selectedGroup() {
        wd.findElement(By.name("selected[]")).click();
    }
}
