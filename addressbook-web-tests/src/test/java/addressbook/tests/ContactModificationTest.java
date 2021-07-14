package addressbook.tests;

import addressbook.model.ContactData;
import addressbook.model.Contacts;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

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
        Contacts before = app.getContactHelper().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withFirstName("Petya").withLastName("Petrov").withEmail("Petrov1990@mail.ru").withId(modifiedContact.getId());
        app.getContactHelper().modification(contact);
        Contacts after = app.getContactHelper().all();
        assertEquals(after.size(), before.size());
        assertThat(after, equalTo(before.withOut(modifiedContact).withAdded(contact)));
    }
}
