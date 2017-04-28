package ru.stqa.jft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.jft.addressbook.model.ContactData;

public class ContactAddingTest extends TestBase {

    @Test
    public void testContactAdding() {
        app.getContactHelper().initContactAdding();
        app.getContactHelper().fillContactForm(new ContactData("Firstname", "Lastname", "SomeCity, Nether Street, 123", "+79876543210", "nomail@domain.no"));
        app.getContactHelper().submitContactForm();
        app.getContactHelper().returnToHomePage();
    }

}
