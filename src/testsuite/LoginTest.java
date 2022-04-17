package testsuite;

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginTest extends BaseTest {

    String baseUrl = "https://www.saucedemo.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void userSholdLoginSuccessfullyWithValid() {
        //find username and send keys
        WebElement userName = driver.findElement(By.id("user-name"));
        userName.sendKeys("standard_user");
        //find password and send keys
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("secret_sauce");
        //find login button element and click
        WebElement logibBtn = driver.findElement(By.id("login-button"));
        logibBtn.click();

        String expectedDisplay = "PRODUCTS";
        String actualDisplay = driver.findElement(By.xpath("//span[contains(text(),'Products')]")).getText();
        //validate expected and actual message
        Assert.assertEquals("Not Matching", expectedDisplay, actualDisplay);

    }

    @Test
    public void verifyThatSixProductsAreDisplayedOnPage() throws InterruptedException {

        WebElement userName = driver.findElement(By.id("user-name"));
        userName.sendKeys("standard_user");

        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("secret_sauce");

        WebElement logibBtn = driver.findElement(By.id("login-button"));
        logibBtn.click();

        int i;
        //create array to validate six products are present on page
        String[] products = {"Sauce Labs Backpack", "Sauce Labs Bike Light", "Sauce Labs Bolt T-Shirt",
                "Sauce Labs Fleece Jacket", "Sauce Labs Onesie", "Test.allTheThings() T-Shirt (Red)"};

        for (i = 1; i < 7; i++) {

            //To verify element is present on page or not.
            String xPath = "/html[1]/body[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[" + i + "]";

            Boolean iselementpresent = driver.findElements(By.xpath(xPath)).size() != 0;

            if (iselementpresent == true) {
                System.out.println(i + " " + products[i - 1] + " is present");
            } else {
                System.out.print("\n" + i + " Product is Not Present On The Page");
            }
        }

    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
