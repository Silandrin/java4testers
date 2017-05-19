package ru.stqa.jft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.jft.addressbook.model.ContactData;
import ru.stqa.jft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactAdding() {
        app.goTo().contactPage();
        Contacts before = app.contact().all();
        File photo = new File("src/test/resources/image.png");
        ContactData contact = new ContactData()
                .withFirstname("Firstname")
                .withLastname("Lastname")
                .withAddress("SomeCity, Nether Street, 123")
                .withMobPhone("+79876543210")
                .withEmail("nomail@domain.no")
                .withGroup("test1")
                .withPhoto(photo);
        app.contact().create(contact, true);
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }

}
