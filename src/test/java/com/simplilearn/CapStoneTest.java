package com.simplilearn;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class CapStoneTest {

    String chromePath = "driver/chromedriver";
    WebDriver driver;

    String baseURL = "http://localhost:4200/";

    @BeforeTest
    public void Setup(){
        System.setProperty("webdriver.chrome.driver", chromePath);
        driver  = new ChromeDriver();
        driver.manage().window().maximize();

        //Register with TestUser as Admin
        driver.get(baseURL);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(150));
        driver.findElement(By.id("linkUserPortal")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(150));
        driver.findElement(By.id("userRegister")).click();

        WebElement fname = driver.findElement(By.name("fname"));
        fname.sendKeys("TestUser");

        WebElement lname = driver.findElement(By.name("lname"));
        lname.sendKeys("TestUser");

        WebElement email = driver.findElement(By.name("email"));
        email.sendKeys("TestUser@test.com");

        WebElement pw = driver.findElement(By.name("password"));
        pw.sendKeys("TestUserPW");

        WebElement cpw = driver.findElement(By.name("confPassword"));
        cpw.sendKeys("TestUserPW");

        driver.findElement(By.id("adminCheck")).click();

        driver.findElement(By.id("submitButton")).click();
    }

    @AfterTest
    public void Teardown(){
        driver.close();
    }

    @Test
    public void OpenPageTest() {
        driver.get(baseURL);
        String title = driver.getTitle();
        String expTitle = "Frontend";
        assert title.equals(expTitle);
    }

    @Test
    public void userPortalTest()  {
        driver.get(baseURL);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(150));
        driver.findElement(By.id("linkUserPortal")).click();
        String url = driver.getCurrentUrl();
        String expUrl = baseURL + "userPortal";
        assert url.equals(expUrl);
    }

    @Test
    public void adminPortalTest()  {
        driver.get(baseURL);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(150));
        driver.findElement(By.id("linkAdminPortal")).click();
        String url = driver.getCurrentUrl();
        String expUrl = baseURL + "adminPortal";
        assert url.equals(expUrl);
    }

    @Test
    public void adminLoginTest()  {
        driver.get(baseURL);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(150));
        driver.findElement(By.id("linkAdminPortal")).click();

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(150));

        WebElement email = driver.findElement(By.name("email"));
        email.sendKeys("TestUser@test.com");

        WebElement pw = driver.findElement(By.name("password"));
        pw.sendKeys("TestUserPW");

        driver.findElement(By.id("submitButton")).click();

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(150));

        String url = driver.getCurrentUrl();
        String expUrl = baseURL + "FoodItem";
        assert url.equals(expUrl);
    }
}

