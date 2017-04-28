package ru.stqa.jft.addressbook;

import org.testng.annotations.Test;

public class ContactAddingTest extends TestBase {

    @Test
    public void testContactAdding() {
        initContactAdding();
        fillContactForm(new ContactData("Firstname", "Lastname", "SomeCity, Nether Street, 123", "+79876543210", "nomail@domain.no"));
        submitContactForm();
        returnToHomePage();
    }

}
