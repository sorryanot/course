package addressbook.tests;

import addressbook.model.GroupData;
import addressbook.model.Groups;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupDeletionTest extends TestBase {

    @Test(dataProvider = "validGroups")
    public void testGroupDeletion(GroupData group) {
        app.getNavigationHelper().goToGroupPage();
        if (app.db().groups().size() == 0) {
            app.getGroupHelper().create(group);
        }

        Groups before = app.db().groups();
        GroupData deletedGroup = before.iterator().next();
        app.getGroupHelper().deletion(deletedGroup);
        assertThat(app.getGroupHelper().count(), equalTo(before.size() - 1));
        Groups after = app.db().groups();
        assertThat(after, equalTo(before.withOut(deletedGroup)));
    }

}
