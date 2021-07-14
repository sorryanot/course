package addressbook.appmanager;

import addressbook.model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

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
    public void modification(int index, ContactData contact) {
        selected(index);
        initContactModification();
        fillContactForm(contact);
        submitContactModification();
        returnToHomePage();
    }
    public void deletion(int index) {
        selected(index);
        deleteSelected();
        returnToHomePage();
    }

    public void returnToHomePage() {
        click(By.linkText("HOME"));
    }

    public void selected(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public List<ContactData> getList() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            List<WebElement> innerElement = element.findElements(By.tagName("td"));
            String lastName = innerElement.get(1).getText();
            String firstName = innerElement.get(2).getText();
            ContactData contact = new ContactData(firstName, lastName, null);
            contacts.add(contact);
        }
        return contacts;
    }

}
