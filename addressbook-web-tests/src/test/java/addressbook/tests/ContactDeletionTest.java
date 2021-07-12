package addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTest extends TestBase {
    @Test
    public void testContactDeletion() {
        app.getNavigationHelper().goToHomePage();
        app.getGroupHelper().selectedGroup();
        app.getContactHelper().deleteSelectedContact();
        app.getNavigationHelper().goToHomePage();

    }
}
