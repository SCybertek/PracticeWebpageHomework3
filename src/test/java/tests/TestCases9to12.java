package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestCases9to12 {


    private WebDriver driver;

    @BeforeMethod
    public void setup(){
        WebDriverManager.chromedriver().version("79").setup();
        driver= new ChromeDriver();
        driver.get("https://practice-cybertekschool.herokuapp.com/");
        driver.findElement(By.linkText("Status Codes")).click();

    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void test9(){

        driver.findElement(By.linkText("200")).click();
        String expected = "This page returned a 200 status code.";
        String actualMessage = driver.findElement(By.xpath("//p")).getText();
        Assert.assertTrue(actualMessage.contains(expected));
    }

    @Test (priority = 100, description = "Verify that following message is displayed - This page returned a 301 status code")
    public void test10(){
        driver.findElement(By.linkText("301")).click();
        String expected = "This page returned a 301 status code.";
        String actualMessage = driver.findElement(By.xpath("//p")).getText();
        Assert.assertTrue(actualMessage.contains(expected));
    }

    @Test (priority = 1, description = "Verify that following message is displayed - This page returned a 500 status code")
    public void test11(){

        driver.findElement(By.linkText("404")).click();
        String expected = "This page returned a 404 status code.";
        String actualMessage = driver.findElement(By.xpath("//p")).getText();

//        if ( actualMessage.contains(expected)){
//            System.out.println("Test Passed");
//        } else {
//            System.out.println("Test Failed");
//        }

        Assert.assertTrue(actualMessage.contains(expected), "The Status Code Does not Exist");
        //hard assertion! if it fails the rest of the script fails!

    }

    @Test (priority = 3, description = "verify that following message is displayed - This page returned a 500 status code")
    public void test12(){

        //href attribute is not easy to verify based on search while inspecting
        driver.findElement(By.linkText("500")).click();

        String message = driver.findElement(By.xpath("//p[contains(text(),'This page returned a 500')]")).getText();
        //driver.findElement(By.tagName("p");

        String expected = "This page returned a 500 status code";

        Assert.assertTrue(message.contains(expected));


    }

}
