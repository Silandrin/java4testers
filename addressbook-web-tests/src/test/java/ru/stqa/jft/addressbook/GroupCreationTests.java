package ru.stqa.jft.addressbook;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;

public class GroupCreationTests {
    ChromeDriver driver;
    
    @BeforeMethod
    public void setUp() throws Exception {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("http://localhost/addressbook/");
        driver.findElement(By.name("user")).click();
        driver.findElement(By.name("user")).clear();
        driver.findElement(By.name("user")).sendKeys("admin");
        driver.findElement(By.name("pass")).click();
        driver.findElement(By.name("pass")).clear();
        driver.findElement(By.name("pass")).sendKeys("secret");
        driver.findElement(By.xpath("//form[@id='LoginForm']/input[3]")).click();

    }
    
    @Test
    public void testGroupCreation() {
        driver.findElement(By.linkText("groups")).click();
        driver.findElement(By.name("new")).click();
        driver.findElement(By.name("group_name")).click();
        driver.findElement(By.name("group_name")).clear();
        driver.findElement(By.name("group_name")).sendKeys("test1");
        driver.findElement(By.name("group_header")).click();
        driver.findElement(By.name("group_header")).clear();
        driver.findElement(By.name("group_header")).sendKeys("test2");
        driver.findElement(By.name("group_footer")).click();
        driver.findElement(By.name("group_footer")).clear();
        driver.findElement(By.name("group_footer")).sendKeys("test3");
        driver.findElement(By.name("submit")).click();
        driver.findElement(By.linkText("group page")).click();
    }
    
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
    
    public static boolean isAlertPresent(ChromeDriver driver) {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
}
