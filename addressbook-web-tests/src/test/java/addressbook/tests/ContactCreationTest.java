package addressbook.tests;

import addressbook.model.ContactData;
import addressbook.model.Contacts;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest extends TestBase {
    @Test
    public void testAddNewContact() {
        app.getNavigationHelper().goToHomePage();
        Contacts before = app.getContactHelper().all();
        ContactData contact = new ContactData()
                .withFirstName("Petya").withLastName("Petrov").withEmail("Petrov1990@mail.ru")
                .withHomePhone("222 2").withMobilePhone("333").withWorkPhone("444").withAddress("sizam street");
        app.getContactHelper().create(contact);
        assertThat(app.getContactHelper().count(), equalTo(before.size() + 1));
        Contacts after = app.getContactHelper().all();
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }
}
