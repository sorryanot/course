package addressbook.tests;

import addressbook.model.ContactData;
import addressbook.model.Contacts;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTest extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.getNavigationHelper().goToHomePage();
        if (app.getContactHelper().all().size() == 0) {
            app.getContactHelper().create(new ContactData().withFirstName("Petya").withLastName("Petrov").withEmail("Petrov1990@mail.ru")
                    .withHomePhone("222").withMobilePhone("333").withWorkPhone("444").withAddress("sizam street"));
        }
    }

    @Test
    public void testContactDeletion() {
        Contacts before = app.getContactHelper().all();
        ContactData deletedContact = before.iterator().next();
        app.getContactHelper().deletion(deletedContact);
        assertThat(app.getContactHelper().count(), equalTo(before.size() - 1));
        Contacts after = app.getContactHelper().all();
        assertThat(after, equalTo(before.withOut(deletedContact)));

    }
}
