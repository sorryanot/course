package addressbook.appmanager;

import addressbook.model.ContactData;
import addressbook.model.Contacts;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;

public class ContactHelper extends BaseHelper {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactForm() {
        click(By.name("submit"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("work"), contactData.getWorkPhone());
        type(By.name("email"), contactData.getEmail());
        type(By.name("address"), contactData.getAddress());

        if(creation){
            if(contactData.getGroup()!=null){
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
            }else {
                Assert.assertFalse(isElementPresent(By.name("new_group")));
            }
        }
    }
    public void fillContactFormWithOutGroup(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("work"), contactData.getWorkPhone());
        type(By.name("email"), contactData.getEmail());
        type(By.name("address"), contactData.getAddress());
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
        fillContactForm(contact,false);
        submitContactForm();
        contactCash = null;
        returnToHomePage();
    }

    public void modification(ContactData contact) {
        selectedById(contact.getId());
        initContactModification();
        fillContactFormWithOutGroup(contact);
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

    public void initContactDetailsById(int id) {
        wd.findElement(By.cssSelector(String.format("a[href='view.php?id=%s']", id))).click();
    }

    public ContactData infoFromDetailsForm(ContactData contact) {
        initContactDetailsById(contact.getId());
        JavascriptExecutor js = (JavascriptExecutor) wd;
        String[] fs = wd.findElement(By.xpath("//*[@id=\"content\"]/b")).getText().split("\\s");
        String firstName = fs[0];
        String lastName = fs[1];
        String email = wd.findElement(By.xpath("//*[@id=\"content\"]/a")).getText();
        String address = (String) js.executeScript("var value = document.evaluate(\"//*[@id='content']/br[1]/following::text()[1]\",document, null, XPathResult.STRING_TYPE, null ); return value.stringValue;");
        String homePhone = ((String) js.executeScript("var value = document.evaluate(\"//*[@id='content']/br[3]/following::text()[1]\",document, null, XPathResult.STRING_TYPE, null ); return value.stringValue;")).substring(2);
        String mobilePhone = ((String) js.executeScript("var value = document.evaluate(\"//*[@id='content']/br[4]/following::text()[1]\",document, null, XPathResult.STRING_TYPE, null ); return value.stringValue;")).substring(2);
        String workPhone = ((String) js.executeScript("var value = document.evaluate(\"//*[@id='content']/br[5]/following::text()[1]\",document, null, XPathResult.STRING_TYPE, null ); return value.stringValue;")).substring(2);

        return new ContactData().withId(contact.getId()).withFirstName(firstName).withLastName(lastName).withAddress(address)
                .withMobilePhone(mobilePhone).withWorkPhone(workPhone).withHomePhone(homePhone).withEmail(email);
    }
}

