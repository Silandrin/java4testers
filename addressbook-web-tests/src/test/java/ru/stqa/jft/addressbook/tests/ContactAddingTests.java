package ru.stqa.jft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.jft.addressbook.model.ContactData;

public class ContactAddingTests extends TestBase {

    @Test
    public void testContactAdding() {
        app.getContactHelper().initContactAdding();
        app.getContactHelper().fillContactForm(new ContactData("Firstname", "Lastname", "SomeCity, Nether Street, 123", "+79876543210", "nomail@domain.no", "test1"), true);
        app.getContactHelper().submitContactForm();
        app.getContactHelper().returnToContactPage();
    }

}
