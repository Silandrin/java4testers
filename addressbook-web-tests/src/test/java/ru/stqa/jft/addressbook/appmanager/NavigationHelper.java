package ru.stqa.jft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class NavigationHelper {
    ChromeDriver driver;

    public void gotoGroupPage() {
        driver.findElement(By.linkText("groups")).click();
    }
}
