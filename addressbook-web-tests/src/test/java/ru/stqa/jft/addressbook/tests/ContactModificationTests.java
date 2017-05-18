package ru.stqa.jft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.jft.addressbook.model.ContactData;
import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().contactPage();
        if (app.contact().list().size() == 0) {
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
        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;
        ContactData contact = new ContactData()
                        .withFirstname("Firstname")
                        .withLastname("Lastname")
                        .withAddress("SomeCity, Nether Street, 123")
                        .withMobPhone("+79876543210")
                        .withEmail("nomail@domain.no");
        app.contact().modify(index, contact);
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
