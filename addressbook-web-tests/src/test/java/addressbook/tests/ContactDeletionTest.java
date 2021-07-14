package addressbook.tests;

import addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;

public class ContactDeletionTest extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.getNavigationHelper().goToHomePage();
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().create(new ContactData().withFirstName("Petya").withLastName("Petrov").withEmail("Petrov1990@mail.ru"));
        }
    }

    @Test
    public void testContactDeletion() {
        Set<ContactData> before = app.getContactHelper().all();
        ContactData deletedContact = before.iterator().next();
        app.getContactHelper().deletion(deletedContact);
        Set<ContactData> after = app.getContactHelper().all();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(deletedContact);
        Assert.assertEquals(before, after);

    }
}
