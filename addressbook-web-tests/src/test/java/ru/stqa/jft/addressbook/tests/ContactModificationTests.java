package ru.stqa.jft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.jft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        app.getNavigationHelper().gotoContactPage();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("Firstname", "Lastname", "SomeCity, Nether Street, 123", "+79876543210", "nomail@domain.no", null), false);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnToContactPage();
    }
}
