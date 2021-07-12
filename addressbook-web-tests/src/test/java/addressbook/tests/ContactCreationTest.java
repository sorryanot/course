package addressbook.tests;

import addressbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactCreationTest extends TestBase {
    @Test
    public void testAddNewContact() {
        app.getContactHelper().goToAddNewContactPage();
        app.getContactHelper().fillContactForm(new ContactData("Petya", "Petrov", "Petrov1990@mail.ru"));
        app.getContactHelper().submitContactForm();
        app.getNavigationHelper().goToHomePage();
    }
}
