package ru.stqa.jft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.jft.addressbook.model.ContactData;
import ru.stqa.jft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactAdding() {
        app.goTo().contactPage();
        Contacts before = app.contact().all();
        ContactData contact = new ContactData()
                .withFirstname("Firstname")
                .withLastname("Lastname")
                .withAddress("SomeCity, Nether Street, 123")
                .withMobPhone("+79876543210")
                .withEmail("nomail@domain.no")
                .withGroup("test1");
        app.contact().create(contact, true);
        Contacts after = app.contact().all();
        assertThat(after.size(), equalTo(before.size() + 1));

        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }

}
