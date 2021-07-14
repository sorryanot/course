package addressbook.tests;

import addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTest extends TestBase {
    @Test
    public void testAddNewContact() {
        app.getNavigationHelper().goToHomePage();
        List<ContactData> before = app.getContactHelper().getList();
        ContactData contact = new ContactData().withFirstName("Petya").withLastName("Petrov").withEmail("Petrov1990@mail.ru");
        app.getContactHelper().create(contact);
        List<ContactData> after = app.getContactHelper().getList();
        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(contact);
        Comparator<? super ContactData> firstName = Comparator.comparing(ContactData::getFirstName);
        before.sort(firstName);
        after.sort(firstName);
        Assert.assertEquals(before, after);
    }
}
