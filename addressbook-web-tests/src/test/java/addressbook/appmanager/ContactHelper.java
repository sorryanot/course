package addressbook.appmanager;

import addressbook.model.ContactData;
import addressbook.model.Contacts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
        type(By.name("home"), simpleData.getHomePhone());
        type(By.name("mobile"), simpleData.getMobilePhone());
        type(By.name("work"), simpleData.getWorkPhone());
        type(By.name("email"), simpleData.getEmail());
        type(By.name("address"), simpleData.getAddress());
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
        contactCash = null;
        returnToHomePage();
    }

    public void modification(ContactData contact) {
        selectedById(contact.getId());
        initContactModification();
        fillContactForm(contact);
        submitContactModification();
        contactCash = null;
        returnToHomePage();
    }

    public void deletion(ContactData contact) {
        selectedById(contact.getId());
        deleteSelected();
        contactCash = null;
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

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    private Contacts contactCash = null;

    public Contacts all() {
        if (contactCash != null) {
            return new Contacts(contactCash);
        }
        contactCash = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            List<WebElement> innerElement = element.findElements(By.tagName("td"));
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String lastName = innerElement.get(1).getText();
            String firstName = innerElement.get(2).getText();
            String allPhones = innerElement.get(5).getText();
            String address = innerElement.get(3).getText();
            String email = innerElement.get(4).getText();
            contactCash.add(new ContactData().withFirstName(firstName).withLastName(lastName).withId(id).
                    withAllPhones(allPhones).withAddress(address).withEmail(email));
        }
        return new Contacts(contactCash);
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getText();
        String homePhone = wd.findElement(By.name("home")).getAttribute("value");
        String mobilePhone = wd.findElement(By.name("mobile")).getAttribute("value");
        String workPhone = wd.findElement(By.name("work")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstName(firstName).withLastName(lastName).withAddress(address)
                .withHomePhone(homePhone).withMobilePhone(mobilePhone).withWorkPhone(workPhone).withEmail(email);

    }

    public void initContactModificationById(int id) {
        wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
    }
}
