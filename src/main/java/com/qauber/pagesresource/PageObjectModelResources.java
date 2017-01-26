package com.qauber.pagesresource;

import com.qauber.pages.*;
import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.awt.*;
import java.util.concurrent.TimeUnit;


//Created by erikfriedlander on 12/17/16.

public class PageObjectModelResources {

    private WebDriver driver;
    private ConfigOOP config;
    private User testCaseUser;
    private UserFactory userFactory;
    private PageResources pages;

    //Helpers
    private HelperResources helpers;

    //Preconditions
    private PreconditionsResources preconditions;

    //Constructor
    public PageObjectModelResources() {
        setUpWithConfigFile();
    }

    /*
        Read ConfigOOP object from config file in ~/QAUberTestConfig. If config file not found or invalid, create a new one using defaults in ConfigOOP constructor.

         */
    protected void setUpWithConfigFile() {
        this.config = ConfigHelper.getConfigFile();
    }

    public void setUpUser(User.UserType userType) {
        userFactory = new UserFactory();
        testCaseUser = userFactory.getUser(userType);
    }

    @Deprecated
    public void setUpWithUser(User.UserType userType, WebDriver webDriver) {
        //get user information
        userFactory = new UserFactory();
        testCaseUser = userFactory.getUser(userType);
        System.out.println(testCaseUser.getUsername());
        System.out.println(testCaseUser.getPassword());

        setUpScript(webDriver);
    }

    private WebDriver chooseDriver(ConfigOOP.BrowserType browserType) {
        if (browserType == ConfigOOP.BrowserType.CHROME) {
            return new ChromeDriver();
        } else if (browserType == ConfigOOP.BrowserType.SAFARI) {
            return new SafariDriver();
        } else if (browserType == ConfigOOP.BrowserType.FIREFOX) {
            return new FirefoxDriver();
        } else {
            return new EdgeDriver(); //(browserType == BrowserType.EDGE)
        }
    }

    public void setUpScript() {
        setUpScript(testConfig());
    }

    private void setUpScript(ConfigOOP config) {
        driver = chooseDriver(config.getBrowserType());
        //implicit wait
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //maximize window for our viewing pleasure
        driver.manage().window().maximize();

        try {
            Thread.sleep(3000); //TODO: is this necessary?
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ////////////////// Done managing WebDriver
        setUpScript(driver);
    }

    @Deprecated //we don't want to pass in driver anymore
    public void setUpScript(WebDriver driver) {
        // Choose web browser/driver from Config

        //implicit wait
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);



        //maximize window for our viewing pleasure
        driver.manage().window().maximize(); //This does not work correctly on OS X with Chrome.
        if (SystemUtils.IS_OS_MAC_OSX && driver instanceof ChromeDriver) { //so let's handle that here
            maximizeScreen(driver);
        }

        ////////////////// Done managing WebDriver

        //Get page resources
        pages = PageResourcesFactory.getPageResources(driver);

        //Helpers
        helpers = new HelperResources(driver);

        //Preconditions
        preconditions = new PreconditionsResources(driver);

    }

    public void breakDownHelper() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }

    @Deprecated
    public void breakDownHelper(WebDriver driver) {
        driver.manage().deleteAllCookies();
        driver.quit();
    }

    ///////GET ALL TEST CASE RESOURCES
    protected WebDriver getDriver() { //visible from subclasses, not public OR private
        return driver;
    }

    //Now for fun... Maximize does not work correctly on Chrome on OS X - here is workaround code from
    //#11 @ https://bugs.chromium.org/p/chromedriver/issues/detail?id=985
    public void maximizeScreen(WebDriver driver) {
        java.awt.Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Point position = new Point(0, 0);
        driver.manage().window().setPosition(position);
        Dimension maximizedScreenSize =
                new Dimension((int) screenSize.getWidth(), (int) screenSize.getHeight());
        driver.manage().window().setSize(maximizedScreenSize);
    }


    //We should eventually replace below with .

    protected LoginPage getLogin() { return pages.getLogin(); }

    protected Header getHeader() { return pages.getHeader(); }

    protected RegistrationPage1 getRegistrationPage1() { return pages.getRegistrationPage1(); }
    protected RegistrationPage2 getRegistrationPage2() { return pages.getRegistrationPage2(); }

    protected NavBar getNavBar() { return pages.getNavBar(); }

    protected ProfilePanel getProfilePanel() { return pages.getProfilePanel(); }

    protected EditProfile getEditProfile() { return pages.getEditProfile(); }

    protected SubscriptionSettings getSubscriptionSettings() {return pages.getSubscriptionSettings();}

    protected Entities getEntities() {return pages.getEntities();}

    protected EntitiesDepartmentSetting getEntitiesDepartmentSettings() {return pages.getEntitiesDepartmentSetting();}

    protected EntitiesPermissionsDialog getEntitiesPermissionsDialog() {return pages.getEntitiesPermissionsDialog();}

    protected Users getUsers() {return pages.getUsers();}

    protected UsersPermissionsDialog getUsersPermissionsDialog() {return pages.getUsersPermissionsDialog();}

    protected EditOrganizationPage getOrganization() {return pages.getEditOrganizationPage();}

    protected CreateOrganization getCreateOrganization() { return pages.getCreateOrganization();}

    protected Reports getReports() {
        return pages.getReports();
    }

    protected ReportsViewReport getReportsViewReport() {return pages.getReportsViewReport(); }

    protected AddReportEnvironment getAddReportEnvironment() {
        return pages.getAddReportEnvironment();
    }

    protected AddReportIdentificationInformation getAddReportIdentificationInformation() {
        return pages.getAddReportIdentificationInformation();
    }

    protected AddReportIdentifiersPage getAddReportIdentifiersPage() {
        return pages.getAddReportIdentifiersPage();
    }

    protected AddReportNavigation getAddReportNavigation() {
        return pages.getAddReportNavigation();
    }

    protected AddReportPhoto getAddReportPhoto() {
        return pages.getAddReportPhoto();
    }

    protected AddReportsOrganization getAddReportOrganization() {
        return pages.getAddReportOrganization();
    }

    protected AddReportSubjectInformationPage getAddReportSubjectInformationPage() {
        return pages.getAddReportSubjectInformationPage();
    }

    protected AddReportVehicle getAddReportVehicle() {
        return pages.getAddReportVehicle();
    }

    protected AddReportPreview getAddReportPreview() { return pages.getAddReportPreview(); }

    protected  CreateSubsciption getCreateSubsciption() {return pages.getCreateSubsciption();}

    @Deprecated //TODO: move into helper method
    protected CompanyCreationSAU getCompanyCreationSAU(){ return pages.getCompanyCreationSAU(); }

    //Users preconditions
    protected PreconditionsResources getPreconditions() { return preconditions; }

    //Helpers
    protected HelperResources getHelpers() { return helpers; }

    //Test Resources - testDriver, testUser, testConfig //TODO: rename? (getTestConfig, etc....) probably not
    protected WebDriver testDriver() {
        return driver;
    }
    protected ConfigOOP testConfig () { return config; }
    protected User testUser() {
        return testCaseUser;
    }
    protected ConfirmRegistrationFromEmail getConfirmRegistrationFromEmail() {
        return pages.getConfirmRegistrationFromEmail();
    }
}
