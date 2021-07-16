package addressbook.tests;

import addressbook.model.GroupData;
import addressbook.model.Groups;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupModificationTest extends TestBase {

    @Test(dataProvider = "validGroups")
    public void testGroupModification(GroupData group) {
        app.getNavigationHelper().goToGroupPage();
        if (app.db().groups().size() == 0) {
            app.getGroupHelper().create(group);
        }
        Groups before = app.db().groups();
        GroupData modifiedGroup = before.iterator().next();
        app.getGroupHelper().modification(group.withId(modifiedGroup.getId()));
        assertThat(app.getGroupHelper().count(), equalTo(before.size()));
        Groups after = app.db().groups();
        assertThat(after, equalTo(before.withOut(modifiedGroup).withAdded(group)));
    }

}
