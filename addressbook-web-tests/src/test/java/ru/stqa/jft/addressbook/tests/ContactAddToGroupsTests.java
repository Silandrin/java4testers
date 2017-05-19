package ru.stqa.jft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.jft.addressbook.model.ContactData;
import ru.stqa.jft.addressbook.model.Contacts;
import ru.stqa.jft.addressbook.model.GroupData;
import ru.stqa.jft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddToGroupsTests extends TestBase {

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
    public void testContactAddToGroup() {
        Contacts contact = app.db().contacts();
        Groups group = app.db().groups();
        ContactData contactForAddToGroup = contact.iterator().next();
        GroupData groupForAddContact = group.iterator().next();
        Groups contactInGroupsBeforeAdded = app.db().contactInGroups();
        app.goTo().contactPage();
        app.contact().addContactToGroup(contactForAddToGroup.getId(), groupForAddContact.getName());
        app.goTo().contactPage();
        Contacts after = app.db().contacts();
        assertThat(after.size(), equalTo(contact.size()));
        Groups contactInGroupsAfterAdded = app.db().contactInGroups();

        assertThat((contactInGroupsAfterAdded), equalTo(new Groups(contactInGroupsBeforeAdded.withAdded(groupForAddContact))));
    }
}

