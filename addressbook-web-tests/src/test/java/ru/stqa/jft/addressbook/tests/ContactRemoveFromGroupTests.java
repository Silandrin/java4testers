package ru.stqa.jft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.jft.addressbook.model.ContactData;
import ru.stqa.jft.addressbook.model.Contacts;
import ru.stqa.jft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactRemoveFromGroupTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
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
            app.goTo().contactPage();
        }

        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData()
                    .withName("test1")
                    .withHeader("header")
                    .withFooter("footer"));
        }
    }

    @Test
    public void testDeleteContactFromGroup(){
        Contacts before = app.db().contacts();
        app.goTo().contactPage();
        ContactData modifiedContact = before.iterator().next();
        GroupData removeGroup = modifiedContact.getGroups().iterator().next();
        app.contact().removeContactFromGroup(removeGroup.getId());
        app.contact().selectContactById(modifiedContact.getId());
        app.contact().click(By.cssSelector("input[name='remove']"));
        Contacts after = app.db().contacts();
        after.remove(modifiedContact);
        ContactData modifiedContact2 = new ContactData();
        for(ContactData contact : before) {
            if (contact.equals(modifiedContact)) {
                contact.getGroups().remove(removeGroup);
                after.add(contact);
            }
        }
        modifiedContact2.getGroups().remove(removeGroup);
        System.out.println(modifiedContact2);

        assertThat(after, equalTo(before));
    }


}