package addressbook;

import org.testng.annotations.Test;

public class GroupDeletionTest extends TestBase{

    @Test
    public void testGroupDeletion(){
        goToGroupPage();
        selectedGroup();
        deleteSelectedGroup();
        returnToGroupPage();
    }

}