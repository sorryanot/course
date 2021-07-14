package addressbook.tests;

import addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class GroupCreationTest extends TestBase {

    @Test
    public void testGroupCreation() {

        app.getNavigationHelper().goToGroupPage();
        List<GroupData> before = app.getGroupHelper().getList();
        GroupData group = new GroupData("test1", null, null);
        app.getGroupHelper().create(group);
        List<GroupData> after = app.getGroupHelper().getList();
        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(group);
        Comparator<? super GroupData> byId = Comparator.comparingInt(GroupData::getId);
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }

}


