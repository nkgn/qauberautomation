package com.qauber.login;


import com.qauber.config.Config;
import com.qauber.testrail.APIClientExtension;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CopyrightSymbolIsPresentOnMainPage {
    APIClientExtension client;
    int runid = Config.getRunID(); //Test Run ID (TestRail - Test Runs & Results - Automation)
    int caseid = 74295;

    @BeforeClass
    public void setUp() {

        client = Config.testRailLogin();

    }

    @Test
    public void StartWebDriver() throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();


        //Open Home page

        driver.get("http://testwave.qabidder.net/#/page/login");
        Thread.sleep(8000);

        //Check year Symbol is Present on the page.

        String year = "";
        year = driver.findElement(By.xpath("//span[@ng-bind='app.year']")).getText();
        Assert.assertEquals(year, "2016");
        Thread.sleep(6000);

        //Check App name is present on the page.
        String App = "";
        App = driver.findElement(By.xpath("//span[@ng-bind='app.name']")).getText();
        Assert.assertEquals(App, "FITS");

        //Check Copyright name is present on the page
        String Copyright = "";
        Copyright = driver.findElement(By.xpath("//span[@ng-bind='app.description']")).getText();
        Assert.assertEquals(Copyright, "FITS Web Application");
        Thread.sleep(6000);

        //ActualResult = driver.findElement(By.xpath("//p[contains(.,'Need to Sign Up?')]")).getText();
        //System.out.println(ActualResult);
        try {
            org.testng.Assert.assertEquals(App,  "FITS");
            org.testng.Assert.assertEquals(year, "2016");
            org.testng.Assert.assertEquals(Copyright,  "FITS Web Application");

        } catch (AssertionError e) { //on fail, run this
            try {
                client.addResult(runid, caseid, 5, e.getLocalizedMessage(), "Mariia's Computer");
                throw e; //Throwing AssertionError
            } catch (IllegalStateException g) {
                g.printStackTrace();
            }
        } catch (Exception e) {
            System.out.println("General Exception in assertEquals");
            e.printStackTrace();

        }

        //If Assert passes, send this to TestRail
        client.addResult(runid, caseid, 1, "Actual value = expected value", "Mariia's Computer");

        driver.quit();

    }
}
