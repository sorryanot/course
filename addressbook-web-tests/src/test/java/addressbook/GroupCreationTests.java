package addressbook;

import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase{

    @Test
    public void testGroupCreation() throws Exception {

        goToGroupPage();
        initGroupCreation();
        fillGroupForm(new GroupData("test1", "test2", "test3"));
        submitGroupCreation();
        returnToGroupPage();
        logout();
    }
    @Test
    public void testAddNewContact(){
        goToaddNewContact();
        fillSingleForm(new SimpleData("Petya", "Petrov", "Petrov1990@mail.ru"));
        submitSingleForm();
        goToHomePage();
    }

}


