package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestCases9to12UsingDataProvider {

    private WebDriver driver;

    //DataProvider returns either 2 Dimensional or Single Dimensional array object
    //one parameter - single
    //2 + parameters - 2 dimensional object

    @DataProvider(name = "testData") //name is a MUST
    public static Object [] testData(){ //better have this method static
        return new Object[]{"200" , "301", "404","500"};
    }

    @Test(dataProvider = "testData") //connecting DataProvider with the Test
    public void statusCodes (String statusCodes) {
        //500 and 404 .. are the parameters
        //that data comes from Data Provider!

        WebDriverManager.chromedriver().version("79").setup();
        driver= new ChromeDriver();
        driver.get("https://practice-cybertekschool.herokuapp.com/");
        driver.findElement(By.linkText("Status Codes")).click();

        //dynamic area - 500 or 404
        driver.findElement(By.linkText(statusCodes)).click();

        String expected = "This page returned a " + statusCodes + " status code.";
        String actualMessage = driver.findElement(By.xpath("//p")).getText();

        Assert.assertTrue(actualMessage.contains(expected));

        driver.quit();

    }
}
