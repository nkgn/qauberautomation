package com.qauber.sanity;

import com.github.javafaker.Faker;
import com.qauber.config.Config;
import com.qauber.pagesresource.PageObjectModelResources;
import com.qauber.pagesresource.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by San Jose on 12/22/16.
 */
public class Delite_Department_AU extends PageObjectModelResources {

    WebDriver driver;
    int sleepTime;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        sleepTime = 5000;
        setUpWithUser(User.UserType.AU, driver); //pass userType and browser. see ~/QAUberTestConfig
        //setUpWithUser creates TestCaseUser, access with getTestCaseUser()
    }

    @Test
    public void editDepartmentAU() throws InterruptedException{
        driver.get(Config.getBaseURL());


        getLogin().loginToWave(testUser().getUsername(), testUser().getPassword());
        Thread.sleep(sleepTime);

        getNavBar().entitiesButton().click();
        Thread.sleep(sleepTime);

        getEntities().organizationList().get(0).click();
        Thread.sleep(sleepTime);

        getEntities().departmentDeleteButtonList().get(0).click();
        Thread.sleep(sleepTime);

        getEntitiesDepartmentSettings().departmentCancelDeliteButton(1).click();
        Thread.sleep(sleepTime);

        getEntities().departmentDeleteButtonList().get(0).click();
        Thread.sleep(sleepTime);

        getEntitiesDepartmentSettings().departmentConfirmDeliteButton(1).click();
        Thread.sleep(sleepTime);




        driver.quit();

    }
}