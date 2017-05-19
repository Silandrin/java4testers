package ru.stqa.jft.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.jft.addressbook.model.ContactData;
import ru.stqa.jft.addressbook.model.Contacts;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContacts() {
        File photo = new File("src/test/resources/image.png");
        List<Object[]> list = new ArrayList<Object[]>();
        list.add(new Object[] {new ContactData().withFirstname("Firstname 1")
                                                .withLastname("Lastname 1")
                                                .withAddress("SomeCity, Nether Street, 1")
                                                .withMobPhone("+19876543210")
                                                .withEmail("nomail_1@domain.no")
                                                .withGroup("test 1")
                                                .withPhoto(photo)});
        list.add(new Object[] {new ContactData().withFirstname("Firstname 2")
                                                .withLastname("Lastname 2")
                                                .withAddress("SomeCity, Nether Street, 2")
                                                .withMobPhone("+29876543210")
                                                .withEmail("nomail_2@domain.no")
                                                .withGroup("test 2")
                                                .withPhoto(photo)});
        list.add(new Object[] {new ContactData().withFirstname("Firstname 3")
                                                .withLastname("Lastname 3")
                                                .withAddress("SomeCity, Nether Street, 3")
                                                .withMobPhone("+39876543210")
                                                .withEmail("nomail_3@domain.no")
                                                .withGroup("test 3")
                                                .withPhoto(photo)});
        return list.iterator();
    }

    @Test(dataProvider = "validContacts")
    public void testContactAdding(ContactData contact) {
        app.goTo().contactPage();
        Contacts before = app.contact().all();
        app.contact().create(contact, true);
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }

}
