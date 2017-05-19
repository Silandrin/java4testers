package ru.stqa.jft.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.jft.addressbook.appmanager.ApplicationManager;
import ru.stqa.jft.addressbook.model.ContactData;
import ru.stqa.jft.addressbook.model.Contacts;
import ru.stqa.jft.addressbook.model.GroupData;
import ru.stqa.jft.addressbook.model.Groups;

import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class TestBase {

    protected static final ApplicationManager app
            = new ApplicationManager(System.getProperty("Browser", BrowserType.CHROME));

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
    }

    @AfterSuite
    public void tearDown() {
        app.stop();
    }

    public void verifyGroupListInUI() {
        if (Boolean.getBoolean("verifyUI")) {
            Groups dbGroups = app.db().groups();
            Groups uiGroups = app.group().all();
            assertThat(uiGroups, equalTo(dbGroups.stream().map((g) ->
                    new GroupData()
                            .withId(g.getId())
                            .withName(g.getName())
            ).collect(Collectors.toSet())));
        }
    }

    public void verifyContactsListInUI() {
        if (Boolean.getBoolean("verifyUI")) {
            Contacts dbContacts = app.db().contacts();
            Contacts uiContacts = app.contact().all();
            assertThat(uiContacts, equalTo(dbContacts.stream().map((g) ->
                    new ContactData()
                            .withId(g.getId())
                            .withFirstname(g.getFirstname())
                            .withLastname(g.getLastname())
                            .withAddress(g.getAddress())
                            .withHomePhone(g.getHomePhone())
                            .withMobPhone(g.getMobPhone())
                            .withWorkPhone(g.getWorkPhone())
                            .withEmail(g.getEmail())
                            .withEmail2(g.getEmail2())
                            .withEmail3(g.getEmail3())
            ).collect(Collectors.toSet())));
        }
    }
}
