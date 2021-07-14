package addressbook.tests;

import addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class GroupDeletionTest extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.getNavigationHelper().goToGroupPage();
        if (!app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().create(new GroupData("test1", "test2", null));
        }
    }

    @Test
    public void testGroupDeletion() {
        List<GroupData> before = app.getGroupHelper().getList();
        int index = before.size() - 1;
        app.getGroupHelper().deletion(index);
        List<GroupData> after = app.getGroupHelper().getList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(index);
        Assert.assertEquals(before, after);
    }

}
