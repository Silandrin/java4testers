package ru.stqa.jft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.jft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDetailsTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().contactPage();
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData()
                            .withFirstname("Firstname")
                            .withLastname("Lastname")
                            .withAddress("SomeCity, Nether Street, 123")
                            .withHomePhone("123 45 67")
                            .withMobPhone("+79876543210")
                            .withWorkPhone("09-87")
                            .withEmail("nomail@domain.no")
                            .withEmail2("no@domain.no")
                            .withEmail3("mail@domain.no")
                    , true);
        }
    }

    @Test
    public void testContactDetails() {
        app.goTo().contactPage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        String contactInfoFromDetailsForm = app.contact().infoFromDetailsForm(contact);


        assertThat(mergedContact(contactInfoFromEditForm), equalTo(contactInfoFromDetailsForm));
    }

    public String mergedContact(ContactData contact) {
        String homePhonePrefix = (contact.getHomePhone() == "") ? "" : "H:";
        String mobPhonePrefix = (contact.getMobPhone() == "") ? "" : "M:";
        String workPhonePrefix = (contact.getWorkPhone() == "") ? "" : "W:";
        return app.contact().purge(contact.getFirstname()
                                    + contact.getLastname()
                                    + contact.getAddress()
                                    + homePhonePrefix
                                    + contact.getHomePhone()
                                    + mobPhonePrefix
                                    + contact.getMobPhone()
                                    + workPhonePrefix
                                    + contact.getWorkPhone()
                                    + contact.getEmail()
                                    + contact.getEmail2()
                                    + contact.getEmail3());

    }
}