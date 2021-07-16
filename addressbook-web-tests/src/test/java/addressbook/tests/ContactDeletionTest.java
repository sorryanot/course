package addressbook.tests;

import addressbook.model.ContactData;
import addressbook.model.Contacts;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTest extends TestBase {


    @Test(dataProvider = "validContact")
    public void testContactDeletion(ContactData contact) {
        app.getNavigationHelper().goToHomePage();
        if (app.getContactHelper().all().size() == 0) {
            app.getContactHelper().create(contact);
        }
        Contacts before = app.getContactHelper().all();
        ContactData deletedContact = before.iterator().next();
        app.getContactHelper().deletion(deletedContact);
        assertThat(app.getContactHelper().count(), equalTo(before.size() - 1));
        Contacts after = app.getContactHelper().all();
        assertThat(after, equalTo(before.withOut(deletedContact)));

    }
}
