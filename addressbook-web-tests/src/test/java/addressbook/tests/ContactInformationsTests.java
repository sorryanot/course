package addressbook.tests;

import addressbook.model.ContactData;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactInformationsTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContact() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))) {
            String json = "";
            String line = reader.readLine();
            while (line != null) {
                json += line;
                line = reader.readLine();
            }
            Gson gson = new Gson();
            List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {
            }.getType());
            return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }
    }

    @Test(dataProvider = "validContact")
    public void testContactInfoDetails(ContactData contact) {
        app.getNavigationHelper().goToHomePage();
        if (app.getContactHelper().all().size() == 0) {
            app.getContactHelper().create(contact);
        }
        app.getNavigationHelper().goToHomePage();
        contact = app.getContactHelper().all().iterator().next();
        ContactData contactInfoFromEditForm = app.getContactHelper().infoFromEditForm(contact);
        ContactData contactInfoFromDetailsForm = app.getContactHelper().infoFromDetailsForm(contact);

        assertThat(contactInfoFromDetailsForm.getFirstName(), equalTo(contactInfoFromEditForm.getFirstName()));
        assertThat(contactInfoFromDetailsForm.getLastName(), equalTo(contactInfoFromEditForm.getLastName()));
        assertThat(contactInfoFromDetailsForm.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
        assertThat(contactInfoFromDetailsForm.getEmail(), equalTo(contactInfoFromEditForm.getEmail()));
        assertThat(cleaned(contactInfoFromDetailsForm.getWorkPhone()), equalTo(cleaned(contactInfoFromEditForm.getWorkPhone())));
        assertThat(cleaned(contactInfoFromDetailsForm.getHomePhone()), equalTo(cleaned(contactInfoFromEditForm.getHomePhone())));
        assertThat(cleaned(contactInfoFromDetailsForm.getMobilePhone()), equalTo(cleaned(contactInfoFromEditForm.getMobilePhone())));


    }

    @Test(dataProvider = "validContact")
    public void testContactInfoEdit(ContactData contact) {
        app.getNavigationHelper().goToHomePage();
        if (app.getContactHelper().all().size() == 0) {
            app.getContactHelper().create(contact);
        }
        app.getNavigationHelper().goToHomePage();
        contact = app.getContactHelper().all().iterator().next();
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
