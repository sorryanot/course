package addressbook.tests;

import addressbook.model.GroupData;
import addressbook.model.Groups;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTest extends TestBase {


    @Test(dataProvider = "validGroups")
    public void testGroupCreation(GroupData group) {

        app.getNavigationHelper().goToGroupPage();
        Groups before = app.db().groups();
        app.getGroupHelper().create(group);
        assertThat(app.getGroupHelper().count(), equalTo(before.size() + 1));
        Groups after = app.db().groups();
        assertThat(after, equalTo(
                before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

}


