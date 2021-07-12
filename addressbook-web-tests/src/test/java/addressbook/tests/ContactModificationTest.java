package addressbook.tests;

import addressbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactModificationTest extends TestBase{

    @Test
    public void testContactModification(){
        app.getNavigationHelper().goToHomePage();
        app.getGroupHelper().selectedGroup();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("Petya", "Petrov", "Petrov1990@mail.ru"));
        app.getContactHelper().submitContactModification();
    }
}
