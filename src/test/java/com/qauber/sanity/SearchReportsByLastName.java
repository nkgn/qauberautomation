package com.qauber.sanity;

import com.qauber.pagesresource.PageObjectModelResources;
import com.qauber.pagesresource.TestRail;
import com.qauber.pagesresource.User;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by Maksim on 12/26/2016.
 */
public class SearchReportsByLastName extends PageObjectModelResources {

    int sleepTime;
    String lastName;

    @BeforeClass
    public void setUp() throws InterruptedException {
        setUpWithConfigFile();
        setUpUser(User.UserType.SAU);

        testConfig().getTestRail().setCaseID(82765);
        testConfig().getTestRail().setTester("MadMax");

        sleepTime = testConfig().getSleepTime();
        setUpScript();

        testDriver().get(testConfig().getBaseURL());
        Thread.sleep(sleepTime);

        getLogin().loginToWave(testUser().getUsername(), testUser().getPassword());
        Thread.sleep(sleepTime);
    }

    @Test
    public void searchReportsByLastName() throws InterruptedException
    {
        getNavBar().reportsButton().click();
        Thread.sleep(sleepTime);
        getReports().publishedOnCheckBox().click();
        Thread.sleep(sleepTime);

        getPreconditions().getReportPreconditions().ensurePublishedReportsAtLeast(10);

        if(getReports().publishedOnCheckBox().isSelected())
            getReports().publishedOnCheckBox().click();

        lastName = getHelpers().getSearchHelper().randomLastName();

        getReports().lastNameField().clear();
        getReports().lastNameField().sendKeys(lastName);
        Thread.sleep(sleepTime);

        try {
            Assert.assertTrue(getReports().searchReportResultID(1).getText().contains(lastName));
        }
        catch (AssertionError e)
        {
            testConfig().getTestRail().addResults(TestRail.TestCaseResult.FAILED, "Search failed: "+e.getLocalizedMessage());
            throw e;
        }
        testConfig().getTestRail().addResults(TestRail.TestCaseResult.PASSED, "Test passed");
    }

    @AfterClass
    public void breakDown()
    {
        breakDownHelper();
    }
}
