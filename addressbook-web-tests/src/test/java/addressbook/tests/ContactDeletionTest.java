package addressbook.tests;

import addressbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactDeletionTest extends TestBase {
    @Test
    public void testContactDeletion() {
        app.getNavigationHelper().goToHomePage();
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Petya", "Petrov", "Petrov1990@mail.ru"));
        }
        app.getContactHelper().selectedContact();
        app.getContactHelper().deleteSelectedContact();
        app.getContactHelper().returnToHomePage();

    }
}
