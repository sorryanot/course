package addressbook.tests;

import addressbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactCreationTest extends TestBase {
    @Test
    public void testAddNewContact() {
        app.getNavigationHelper().goToHomePage();
        app.getContactHelper().initContactCreation();
        app.getContactHelper().fillContactForm(new ContactData("Petya", "Petrov", "Petrov1990@mail.ru"));
        app.getContactHelper().submitContactForm();
        app.getNavigationHelper().goToHomePage();
    }
}
