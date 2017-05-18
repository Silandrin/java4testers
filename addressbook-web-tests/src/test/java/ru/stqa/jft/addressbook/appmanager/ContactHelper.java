package ru.stqa.jft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.jft.addressbook.model.ContactData;
import ru.stqa.jft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver driver) {
        super(driver);
    }

    public void returnToContactPage() {
        click(By.linkText("home page"));
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
            new Select(driver.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void initContactAdding() {
        click(By.linkText("add new"));
    }

    public void selectContact(int index) {
        driver.findElements(By.name("selected[]")).get(index).click();
    }

    public void deleteSelectedContacts() {
        click(By.cssSelector("input[value=Delete]"));
        acceptAlert();
    }

    public void initContactModification(int index) {
        driver.findElements(By.cssSelector("img[title=Edit]")).get(index).click();
    }

    public void submitContactModification() {
        click(By.cssSelector("input[value=Update]"));
    }

    public void addContact(ContactData contact, boolean creation) {
        initContactAdding();
        fillContactForm(contact, creation);
        submitContactForm();
        returnToContactPage();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getContactCount() {
        return driver.findElements(By.name("selected[]")).size();
    }

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = driver.findElements(By.cssSelector("tr[name = entry]"));
        for (WebElement element : elements) {
            String id = element.findElement(By.tagName("input")).getAttribute("value");
            String firstName = element.findElement(By.cssSelector("tr[name = entry] td:nth-child(3)")).getText();
            String lastName = element.findElement(By.cssSelector("tr[name = entry] td:nth-child(2)")).getText();;
            ContactData contact = new ContactData(id, firstName, lastName, null, null, null, null);
            contacts.add(contact);
        }
        return contacts;
    }
}
