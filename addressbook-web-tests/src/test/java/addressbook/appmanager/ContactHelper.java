package addressbook.appmanager;

import addressbook.model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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

    public void deleteSelectedContact() {
        click(By.xpath("//input[@value='DELETE']"));
        wd.switchTo().alert().accept();
    }

    public void initContactModification() {
        click(By.xpath("//img[@title='EDIT']"));
    }

    public void submitContactModification() {
        click(By.xpath("//input[@value='UPDATE']"));
    }


    public void createContact(ContactData contact) {
        initContactCreation();
        fillContactForm(contact);
        submitContactForm();
        returnToHomePage();
    }

    private void returnToHomePage() {
        click(By.linkText("HOME"));
    }

    public void selectedContact() {
        click(By.name("selected[]"));
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }
}
