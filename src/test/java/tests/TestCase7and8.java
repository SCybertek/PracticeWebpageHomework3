package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestCase7and8 {

    private WebDriver driver;

    @BeforeMethod
    public void setup(){
        WebDriverManager.chromedriver().version("79").setup();
        driver= new ChromeDriver();
        driver.get("https://practice-cybertekschool.herokuapp.com/");


    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void test7() throws Exception{
        driver.findElement(By.linkText("File Upload")).click();
        WebElement button = driver.findElement(By.id("file-upload"));
        String file = System.getProperty("user.home") + "/Downloads/Test Case 6.txt";
        System.out.println("file = " + file);
        button.sendKeys(file);
        Thread.sleep(2000);
        driver.findElement(By.id("file-submit")).click();

    }

    @Test
    public void test8() throws Exception{
        driver.findElement(By.linkText("Autocomplete")).click();
        driver.findElement(By.id("myCountry")).sendKeys("United States of America");
        driver.findElement(By.cssSelector("[class='btn btn-primary']")).click();

        String expected = "You selected: United States of America";
        String actual = driver.findElement(By.id("result")).getText();
        Thread.sleep(3000);

        Assert.assertEquals(expected,actual);
    }

}
