package addressbook.tests;

import addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

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
        List<ContactData> before = app.getContactHelper().getList();
        int index = before.size() - 1;
        ContactData contact = new ContactData().withFirstName("Petya").withLastName("Petrov").withEmail("Petrov1990@mail.ru");
        app.getContactHelper().modification(index, contact);
        List<ContactData> after = app.getContactHelper().getList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(contact);
        Comparator<? super ContactData> firstName = Comparator.comparing(ContactData::getFirstName);
        before.sort(firstName);
        after.sort(firstName);
        Assert.assertEquals(before, after);
    }
}
