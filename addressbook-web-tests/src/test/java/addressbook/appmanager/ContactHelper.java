package addressbook.appmanager;

import addressbook.model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends BaseHelper {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactForm() {
        click(By.name("submit"));
    }

    public void fillContactForm(ContactData simpleData) {
        type(By.name("firstname"), simpleData.getFirstName());
        type(By.name("lastname"), simpleData.getLastName());
        type(By.name("email"), simpleData.getEmail());
    }

    public void initContactCreation() {
        click(By.linkText("ADD_NEW"));
    }

    public void deleteSelected() {
        click(By.xpath("//input[@value='DELETE']"));
        wd.switchTo().alert().accept();
    }

    public void initContactModification() {
        click(By.xpath("//img[@title='EDIT']"));
    }

    public void submitContactModification() {
        click(By.xpath("//input[@value='UPDATE']"));
    }


    public void create(ContactData contact) {
        initContactCreation();
        fillContactForm(contact);
        submitContactForm();
        returnToHomePage();
    }

    public void modification(ContactData contact) {
        selectedById(contact.getId());
        initContactModification();
        fillContactForm(contact);
        submitContactModification();
        returnToHomePage();
    }

    public void deletion(ContactData contact) {
        selectedById(contact.getId());
        deleteSelected();
        returnToHomePage();
    }

    public void returnToHomePage() {
        click(By.linkText("HOME"));
    }

    public void selectedById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public Set<ContactData> all() {
        Set<ContactData> contacts = new HashSet<>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            List<WebElement> innerElement = element.findElements(By.tagName("td"));
            String lastName = innerElement.get(1).getText();
            String firstName = innerElement.get(2).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            contacts.add(new ContactData().withFirstName(firstName).withLastName(lastName).withId(id));
        }
        return contacts;
    }

}
