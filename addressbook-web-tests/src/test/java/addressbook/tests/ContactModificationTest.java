package addressbook.tests;

import addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

public class ContactModificationTest extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.getNavigationHelper().goToHomePage();
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().create(new ContactData().withFirstName("Petya").withLastName("Petrov").withEmail("Petrov1990@mail.ru"));
        }
    }

    @Test
    public void testContactModification() {
        Set<ContactData> before = app.getContactHelper().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withFirstName("Petya").withLastName("Petrov").withEmail("Petrov1990@mail.ru").withId(modifiedContact.getId());
        app.getContactHelper().modification(contact);
        Set<ContactData> after = app.getContactHelper().all();
        Assert.assertEquals(after.size(), before.size());

        before.remove(modifiedContact);
        before.add(contact);
        Assert.assertEquals(before, after);
    }
}
