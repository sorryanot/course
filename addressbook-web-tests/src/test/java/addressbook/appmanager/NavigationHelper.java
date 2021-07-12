package addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper {
    private WebDriver wd;

    public NavigationHelper(WebDriver wd) {
        this.wd=wd;
    }

    public void goToHomePage() {
        wd.findElement(By.linkText("HOME")).click();
    }

    public void goToGroupPage() {
        wd.findElement(By.linkText("GROUPS")).click();
    }
}
