package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class TestScript1to5 {

    private WebDriver driver;
    private By birthInputBy = By.name("birthday");
    private By checkBoxC = By.id("inlineCheckbox1");
    private By checkBoxJava = By.id("inlineCheckbox2");
    private By checkBoxJS = By.id("inlineCheckbox3");
    private By firstNameBy = By.name("firstname");
    private By lastNameBy = By.name("lastname");
    private By usernameBy = By.name("username");
    private By emailBy = By.name("email");
    private By passwordBy = By.name("password");
    private By phoneBy = By.name("phone");
    private By maleBy = By.cssSelector("[value='male']");
    private By femaleBy = By.cssSelector("[value='female']");
    private By otherBy = By.cssSelector("[value='other']");
    private By departmentBy = By.name("department");
    private By jobTitleBy = By.name("job_title");
    private By submitBy = By.id("wooden_spoon");
    private By messageWellDoneBy = By.xpath("//h4/following-sibling::p");




    @Test (description = "Entering wrong date of birth as an input")
    public void negativeDateOfBirthTest() {
        driver.findElement(birthInputBy).sendKeys("wrong_dob");
        String actual = driver.findElement(By.xpath("//small[@data-bv-validator='date'] ")).getText();
        String expected = "The date of birth is not valid";
        Assert.assertEquals(actual,expected,"The warning message is different");
    }

    @Test (description = "Verify that programming languages are displayed")
    public void programmingLanguageOptions() {
        WebElement cPlusPlusBox = driver.findElement(checkBoxC);
        WebElement javaBox = driver.findElement(checkBoxJava);
        WebElement javaScriptBox = driver.findElement(checkBoxJS);

        Assert.assertTrue(cPlusPlusBox.isDisplayed(),"No C++");
        Assert.assertTrue(javaBox.isDisplayed(), "No Java");
        Assert.assertTrue(javaScriptBox.isDisplayed(), "No JavaScript");
    }

    @Test (description = "Verify warning message after entering only one alphabetic char into first name input box")
    public void nameNegativeTest(){
            driver.findElement(firstNameBy).sendKeys("a");
            String actual = driver.findElement(By.xpath("//small[text()= 'first name must be more than 2 and less than 64 characters long']")).getText();
            String expected = "first name must be more than 2 and less than 64 characters long";
            Assert.assertEquals(actual,expected);
    }

    @Test (description = "Verify warning message after entering only one alphabetic char into last name input box")
    public void lastNameNegativeTest(){
        driver.findElement(lastNameBy).sendKeys("a");
        String actual = driver.findElement(By.xpath("//small[text()= 'The last name must be more than 2 and less than 64 characters long']")).getText();
        String expected = "The last name must be more than 2 and less than 64 characters long";
        Assert.assertEquals(actual,expected);
    }

    @Test (description = "Verifying successful submission of the Registration Form")
    public void registrationVerificationTest(){
        driver.findElement(firstNameBy).sendKeys("Cecily");
        driver.findElement(lastNameBy).sendKeys("Strong");
        driver.findElement(usernameBy).sendKeys("cstrong");
        driver.findElement(emailBy).sendKeys("abc@gmail.com");
        driver.findElement(passwordBy).sendKeys("passkey12");
        driver.findElement(phoneBy).sendKeys("123-977-1467");
        driver.findElement(femaleBy).click();
        driver.findElement(birthInputBy).sendKeys("12/23/1976");
        Select select = new Select(driver.findElement(departmentBy));
        select.selectByVisibleText("Mayor's Office");
        Select select1 = new Select(driver.findElement(jobTitleBy) );
        select1.selectByVisibleText("Developer");
        driver.findElement(checkBoxJS).click();
        driver.findElement(submitBy).click();
        WebElement message = driver.findElement(messageWellDoneBy);
        Assert.assertTrue(message.isDisplayed() );

    }

    @BeforeMethod
    public void setup(){
        WebDriverManager.chromedriver().version("79").setup();
        driver= new ChromeDriver();
        driver.get("https://practice-cybertekschool.herokuapp.com/");
        driver.findElement(By.linkText("Registration Form")).click();
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
