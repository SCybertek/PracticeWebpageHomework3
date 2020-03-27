package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.BrowserFactory;

public class TestCase6 {

    private WebDriver driver;

    @Test
    public void test6() throws Exception {

        driver = BrowserFactory.getDriver("chrome");
        //driver.get("https://www.fakemail.net/");
        driver.get("https://www.tempmailaddress.com/");

        String emailString = driver.findElement(By.id("email")).getText();

        driver.navigate().to("https://practice-cybertekschool.herokuapp.com");

        //clicking on Sign Up For Mailing List
        driver.findElement(By.linkText("Sign Up For Mailing List")).click();
        driver.findElement(By.name("full_name")).sendKeys("Masha");
        driver.findElement(By.name("email")).sendKeys(emailString);

        driver.findElement(By.tagName("button")).click();

        String actual = driver.findElement(By.name("signup_message")).getText();
        String expected = "Thank you for signing up. Click the button below to return to the home page.";
        Thread.sleep(1000);
        Assert.assertEquals(actual, expected);

        driver.navigate().back();
        driver.navigate().back();
        driver.navigate().back();
        Thread.sleep(1000);

        //refresh : maybe needed
        //driver.navigate().refresh();//now the connection between prior elements is lost : like emailString
        String expectedFrom = "do-not-reply@practice.cybertekschool.com";
        String actualFrom = driver.findElement(By.xpath("//table//*[contains(text(),'do-not-reply@practice.cybertekschool.com')]")).getText().trim();
        String expectedSubject = "Thanks for subscribing to practice.cybertekschool.com!";
        String actualSubject = driver.findElement(By.xpath("//table//*[contains(text(),'Thanks for subscribing to practice.cybertekschool.com!')]")).getText();

        Assert.assertEquals(expectedFrom, actualFrom);
        Assert.assertEquals(expectedSubject, actualSubject);

        driver.quit();


    }
}
