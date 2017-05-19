package ru.stqa.jft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.jft.addressbook.model.ContactData;
import ru.stqa.jft.addressbook.model.Contacts;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver driver) {
        super(driver);
    }

    public void returnToContactPage() {
        click(By.linkText("home"));
    }

    public void submitContactForm() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("address"), contactData.getAddress());
        type(By.name("mobile"), contactData.getMobPhone());
        type(By.name("email"), contactData.getEmail());

        if (creation) {
       //     new Select(driver.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        //} else {
        //    Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void initContactAdding() {
        click(By.linkText("add new"));
    }

    public void selectContactById(int id) {
        driver.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void deleteSelectedContacts() {
        click(By.cssSelector("input[value=Delete]"));
        acceptAlert();
    }

    public void initContactModificationById(int id) {
        driver.findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")).click();
    }

    public void submitContactModification() {
        click(By.cssSelector("input[value=Update]"));
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstname = driver.findElement(By.name("firstname")).getAttribute("value");
        String lastname = driver.findElement(By.name("lastname")).getAttribute("value");
        String address = driver.findElement(By.name("address")).getAttribute("value");
        String home = driver.findElement(By.name("home")).getAttribute("value");
        String mobile = driver.findElement(By.name("mobile")).getAttribute("value");
        String work = driver.findElement(By.name("work")).getAttribute("value");
        String email = driver.findElement(By.name("email")).getAttribute("value");
        String email2 = driver.findElement(By.name("email2")).getAttribute("value");
        String email3 = driver.findElement(By.name("email3")).getAttribute("value");
        driver.navigate().back();
        return new ContactData()
                .withId(contact.getId())
                .withFirstname(firstname)
                .withLastname(lastname)
                .withAddress(address)
                .withHomePhone(home)
                .withMobPhone(mobile)
                .withWorkPhone(work)
                .withEmail(email)
                .withEmail2(email2)
                .withEmail3(email3);
    }

    public String infoFromDetailsForm(ContactData contact) {
        driver.findElement(By.cssSelector("a[href='view.php?id=" + contact.getId() + "']")).click();
        String details = driver.findElement(By.cssSelector("div#content")).getText();
        driver.navigate().back();
        return purge(details);
    }

    public String purge(String s) {
        return s.replaceAll("\\n", "").replaceAll("\\s", "");
    }

    public void create(ContactData contact, boolean creation) {
        initContactAdding();
        fillContactForm(contact, creation);
        submitContactForm();
        contactCache = null;
        returnToContactPage();
    }

    public void modify(ContactData contact) {
        initContactModificationById(contact.getId());
        fillContactForm(contact, false);
        submitContactModification();
        contactCache = null;
        returnToContactPage();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContacts();
        contactCache = null;
        returnToContactPage();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int count() {
        return driver.findElements(By.name("selected[]")).size();
    }

    private Contacts contactCache = null;

    public Contacts all() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> elements = driver.findElements(By.cssSelector("tr[name = entry]"));
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String lastName = element.findElement(By.cssSelector("tr[name = entry] td:nth-child(2)")).getText();
            String firstName = element.findElement(By.cssSelector("tr[name = entry] td:nth-child(3)")).getText();
            String address = element.findElement(By.cssSelector("tr[name = entry] td:nth-child(4)")).getText();
            String allEmails = element.findElement(By.cssSelector("tr[name = entry] td:nth-child(5)")).getText();
            String allPhones = element.findElement(By.cssSelector("tr[name = entry] td:nth-child(6)")).getText();
            contactCache.add(new ContactData()
                    .withId(id)
                    .withFirstname(firstName)
                    .withLastname(lastName)
                    .withAddress(address)
                    .withAllEmails(allEmails)
                    .withAllPhones(allPhones));
        }
        return new Contacts(contactCache);
    }

    public void addContactToGroup(int id, String name) {
        selectContactById(id);
        selectGroupInAddDropdownList(name);
        initAddContactInGroup();
    }

    private void selectGroupInAddDropdownList(String name) {
        new Select(driver.findElement(By.name("to_group"))).selectByVisibleText(name);
    }

    private void initAddContactInGroup() {
        click(By.name("add"));
    }

    public void removeContactFromGroup(int id) {
        driver.findElement(By.cssSelector("select[name='group']>option[value='" + id + "']")).click();
    }
}
