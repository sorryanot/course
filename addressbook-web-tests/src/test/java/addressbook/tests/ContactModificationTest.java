package addressbook.tests;

import addressbook.model.ContactData;
import addressbook.model.Contacts;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTest extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.getNavigationHelper().goToHomePage();
        if (app.getContactHelper().all().size() == 0) {
            app.getContactHelper().create(new ContactData()
                    .withFirstName("Petya").withLastName("Petrov").withEmail("Petrov1990@mail.ru")
                    .withHomePhone("222").withMobilePhone("333").withWorkPhone("444").withAddress("sizam street"));
        }
    }

    @Test
    public void testContactModification() {
        Contacts before = app.getContactHelper().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withFirstName("Petya").withLastName("Petrov").withEmail("Petrov1990@mail.ru").withId(modifiedContact.getId());
        app.getContactHelper().modification(contact);
        assertThat(app.getContactHelper().count(), equalTo(before.size()));
        Contacts after = app.getContactHelper().all();
        assertThat(after, equalTo(before.withOut(modifiedContact).withAdded(contact)));
    }
}
