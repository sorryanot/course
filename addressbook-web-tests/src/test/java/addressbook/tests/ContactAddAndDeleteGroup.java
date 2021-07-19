package addressbook.tests;

import addressbook.model.ContactData;
import addressbook.model.Contacts;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactAddAndDeleteGroup extends TestBase {

    @Test(dataProvider = "validContact")
    public void testContactAddInGroup(ContactData contact) {

        app.getNavigationHelper().goToHomePage();
        if (app.db().contacts().size() == 0) {
            app.getContactHelper().create(contact);
        }
        Contacts before = app.db().contacts();
        ContactData addContactToGroup = before.iterator().next();
        app.getContactHelper().addInGroup(addContactToGroup);
        assertThat(app.getContactHelper().count(), equalTo(before.size()));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before));
    }

    @Test(dataProvider = "validContact")
    public void testContactDeleteInGroup(ContactData contact) {
        app.getNavigationHelper().goToHomePage();
        if (app.db().contacts().size() == 0) {
            app.getContactHelper().create(contact);
            Contacts before = app.db().contacts();
            ContactData addContactToGroup = before.iterator().next();
            app.getContactHelper().addInGroup(addContactToGroup);
        }
        Contacts before = app.db().contacts();
        ContactData deleteContactFromGroup = before.iterator().next();
        app.getContactHelper().deleteFromGroup(deleteContactFromGroup);
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before));

    }
}
