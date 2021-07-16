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
        if (app.db().contacts().size() == 0) {
            app.getContactHelper().create(contact);
        }
        Contacts before = app.db().contacts();
        ContactData deletedContact = before.iterator().next();
        app.getContactHelper().deletion(deletedContact);
        assertThat(app.getContactHelper().count(), equalTo(before.size() - 1));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.withOut(deletedContact)));

    }
}
