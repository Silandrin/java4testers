package ru.stqa.jft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.jft.addressbook.model.ContactData;
import java.util.HashSet;
import java.util.List;

public class ContactAddingTests extends TestBase {

    @Test
    public void testContactAdding() {
        app.getNavigationHelper().gotoContactPage();
        List<ContactData> before = app.getContactHelper().getContactList();
        ContactData contact = new ContactData("Firstname", "Lastname", "SomeCity, Nether Street, 123", "+79876543210", "nomail@domain.no", "test1");
        app.getContactHelper().addContact(contact, true);
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);

        int max = 0;
        for (ContactData c : after) {
            if (c.getId() > max) {
                max = c.getId();
            }
        }
        contact.setId(max);
        before.add(contact);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }

}
