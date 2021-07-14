package addressbook.tests;

import addressbook.model.GroupData;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTest extends TestBase {

    @Test
    public void testGroupCreation() {

        app.getNavigationHelper().goToGroupPage();
        Set<GroupData> before = app.getGroupHelper().all();
        GroupData group = new GroupData().withName("test1");
        app.getGroupHelper().create(group);
        Set<GroupData> after = app.getGroupHelper().all();
        Assert.assertEquals(after.size(), before.size() + 1);

        group.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt());
        before.add(group);
        Assert.assertEquals(before, after);
        assertThat(after, equalTo(before));
    }

}


