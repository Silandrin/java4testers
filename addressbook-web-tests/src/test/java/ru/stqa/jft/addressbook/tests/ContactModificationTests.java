package ru.stqa.jft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.jft.addressbook.model.ContactData;
import java.util.Set;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().contactPage();
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData()
                            .withFirstname("Firstname")
                            .withLastname("Lastname")
                            .withAddress("SomeCity, Nether Street, 123")
                            .withMobPhone("+79876543210")
                            .withEmail("nomail@domain.no")
                            .withGroup("test1")
                    , true);
        }
    }

    @Test
    public void testContactModification() {
        Set<ContactData> before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                        .withId(modifiedContact.getId())
                        .withFirstname("Firstname")
                        .withLastname("Lastname")
                        .withAddress("SomeCity, Nether Street, 123")
                        .withMobPhone("+79876543210")
                        .withEmail("nomail@domain.no");
        app.contact().modify(contact);
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size());

        before.remove(modifiedContact);
        before.add(contact);
        Assert.assertEquals(before, after);
    }
}
