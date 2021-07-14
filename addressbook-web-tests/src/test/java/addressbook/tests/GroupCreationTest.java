package addressbook.tests;

import addressbook.model.GroupData;
import addressbook.model.Groups;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTest extends TestBase {

    @Test
    public void testGroupCreation() {

        app.getNavigationHelper().goToGroupPage();
        Groups before = app.getGroupHelper().all();
        GroupData group = new GroupData().withName("test1");
        app.getGroupHelper().create(group);
        assertThat(app.getGroupHelper().count(), equalTo(before.size() + 1));
        Groups after = app.getGroupHelper().all();
        assertThat(after, equalTo(
                before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

}


