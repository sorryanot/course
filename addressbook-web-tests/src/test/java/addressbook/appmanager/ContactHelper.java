package addressbook.appmanager;

import addressbook.model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactHelper {
    private WebDriver wd;

    public ContactHelper(WebDriver wd) {
        this.wd=wd;
    }

    public void submitContactForm() {
        wd.findElement(By.name("submit")).click();
    }

    public void fillContactForm(ContactData simpleData) {
        wd.findElement(By.name("firstname")).sendKeys(simpleData.getFirstName());
        wd.findElement(By.name("lastname")).sendKeys(simpleData.getLastName());
        wd.findElement(By.name("email")).sendKeys(simpleData.getEmail());
    }

    public void goToAddNewContactPage() {
        wd.findElement(By.linkText("ADD_NEW")).click();
    }
    public void deleteSelectedContact() {
        wd.findElement(By.xpath("//input[@value='DELETE']")).click();
        wd.switchTo().alert().accept();
    }
}
