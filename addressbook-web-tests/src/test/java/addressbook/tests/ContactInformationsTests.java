package addressbook.tests;

import addressbook.model.ContactData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactInformationsTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.getNavigationHelper().goToHomePage();
        if (app.getContactHelper().all().size() == 0) {
            app.getContactHelper().create(new ContactData()
                    .withFirstName("Petya").withLastName("Petrov").withEmail("Petrov1990@mail.ru")
                    .withHomePhone("222 222").withMobilePhone("+333").withWorkPhone("444").withAddress("sizam street"));
        }
    }

    @Test
    public void testContactPhones() {
        app.getNavigationHelper().goToHomePage();
        ContactData contact = app.getContactHelper().all().iterator().next();
        ContactData contactInfoFromEditForm = app.getContactHelper().infoFromEditForm(contact);

        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
        assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
        assertThat(contact.getEmail(), equalTo(contactInfoFromEditForm.getEmail()));
    }

    private String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
                .stream().filter((s) -> !s.equals(""))
                .map(ContactInformationsTests::cleaned)
                .collect(Collectors.joining("\n"));

    }

    public static String cleaned(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }
}
