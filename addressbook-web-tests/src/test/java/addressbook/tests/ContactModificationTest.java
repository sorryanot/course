package addressbook.tests;

import addressbook.model.ContactData;
import addressbook.model.Contacts;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTest extends TestBase {


    @Test(dataProvider = "validContact")
    public void testContactModification(ContactData contact) {
        app.getNavigationHelper().goToHomePage();
        if (app.db().contacts().size() == 0) {
            app.getContactHelper().create(contact);
        }
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        app.getContactHelper().modification(contact.withId(modifiedContact.getId()));
        assertThat(app.getContactHelper().count(), equalTo(before.size()));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.withOut(modifiedContact).withAdded(contact)));

    }
}
