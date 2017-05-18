package ru.stqa.jft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.jft.addressbook.model.ContactData;

public class ContactAddingTests extends TestBase {

    @Test
    public void testContactAdding() {
        app.getNavigationHelper().gotoContactPage();
        int before = app.getContactHelper().getContactCount();
        app.getContactHelper().addContact(new ContactData("Firstname", "Lastname", "SomeCity, Nether Street, 123", "+79876543210", "nomail@domain.no", "test1"), true);
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before + 1);
    }

}
