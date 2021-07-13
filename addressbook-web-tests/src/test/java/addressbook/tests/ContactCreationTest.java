package addressbook.tests;

import addressbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactCreationTest extends TestBase {
    @Test
    public void testAddNewContact() {
        app.getNavigationHelper().goToHomePage();
        app.getContactHelper().createContact(new ContactData("Petya", "Petrov", "Petrov1990@mail.ru"));
    }
}
