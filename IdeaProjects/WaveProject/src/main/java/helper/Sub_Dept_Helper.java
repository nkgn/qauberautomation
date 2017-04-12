package helper;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

/**
 * Created by anbus on 3/30/2017.
 */
public class Sub_Dept_Helper {
    WebDriver driver;
    public int k;
    public int n;
    By Entities = By.xpath("//span[@class='ng-binding' and contains(text(),'Entities')]");
    By AddEntity_button = By.xpath("//h3[@class='h3-alt ng-scope']//button");
    By Entity_Name = By.name("name");
    By Address1 = By.name("address1");
    By City = By.name("city");
    //By State_dropdown = By.name("state");
    By Postalcode = By.name("zip");
    By NextStep_button = By.xpath("//div[@class='panel-footer text-right']//span[text()='Next step']");
    By Finish_button = By.xpath("//div[@class='panel-footer text-right']//span[text()='Finish']");
    By paypal_email = By.xpath("//input[@id='login_email']");
    By paypal_pwd = By.xpath("//input[@id='login_password']");
    By login = By.xpath("//input[@id='submitLogin']");
    By Agree_button = By.xpath("//input[@id='continue']");
    By NextStep_button1 = By.xpath("//button[@type='button']/span[text()='Next step']");
    By NextStep_button2 = By.xpath("//button[@class='btn btn-info btn-labeled']/span[text()='Next step']");
    By PaypalLogin_link = By.xpath("//input[@id='loadLogin']");
    By AddDept = By.xpath("//div[@class='content-wrapper ng-scope ng-fadeInUp']//button [text()='Add Department']");
    By DeptName = By.xpath("//form//input[@name='name']");
   // By Add_Button = By.cssSelector(".clearfix > button:nth-child(1)");
    By Add_Button = By.xpath("//div[@class='ngdialog-message' ]//form//button");

    public Sub_Dept_Helper(WebDriver driver) {

        this.driver = driver;
    }

    public void sau_Add_Company() throws InterruptedException {

        /** click on entities from left menu panel */
        driver.findElement(Entities).click();
        Thread.sleep(2000);

        /** Click add entity */
        driver.findElement(AddEntity_button).click();

        /** Passing random string as Entity Name */
        String randomStr = Long.toHexString(Double.doubleToLongBits(Math.random()));
        driver.findElement(Entity_Name).sendKeys(randomStr);
        Thread.sleep(1000);
        driver.findElement(Address1).sendKeys("main st");
        driver.findElement(City).sendKeys("cupertino");
        Select state_dd = new Select(driver.findElement(By.name("state")));
        state_dd.selectByVisibleText("California");
        driver.findElement(Postalcode).sendKeys("1234");
        System.out.println("AddEntity - Expected:" + randomStr);
        Thread.sleep(1000);
        driver.findElement(NextStep_button).click();
        Thread.sleep(1000);
        driver.findElement(NextStep_button1).click();
        Thread.sleep(3000);
        driver.findElement(NextStep_button2).click();
        Thread.sleep(1000);
        driver.findElement(Finish_button).click();
        Thread.sleep(3000);
        /** click on Paypal link to subscribe entity */
        driver.findElement(PaypalLogin_link).click();
        Thread.sleep(3000);
        driver.findElement(paypal_email).clear();
        /**Paypal login credentials */
        driver.findElement(paypal_email).sendKeys("anbu.sundara-buyer@gmail.com");
        driver.findElement(paypal_pwd).sendKeys("tester17!");
        /** Click login */
        driver.findElement(login).click();
        Thread.sleep(3000);
        /** Agree the terms & click agree button */
        driver.findElement(Agree_button).click();
        Thread.sleep(4500);

        /**
        Finding the Entity which was subscribed & click on that to add department
         */
        List<WebElement> allRows = driver.findElements(By.xpath("//table//tr[@class='ng-scope']"));
        System.out.println(" No of rows found :" + allRows.size());

        for (int j = 1; j <allRows.size(); j++) {
            System.out.println("TEXT :" + allRows.get(j).getText() + " Random Str :" + randomStr);
            System.out.println("Row num :" + j);
            if (allRows.get(j).getText().contains(randomStr)) {
                // Assert.assertEquals(allRows.get(j).getText(), randomStr);
                System.out.println("Found newly added Entity at " + j +"row");
                k=j+1;
                driver.findElement(By.xpath("//table//tr["+k+"]//div[@class='col-md-6']//a")).click();
                System.out.println("Newly created entity " + randomStr + " is clicked");

            }
        }





        Thread.sleep(1000);
        /** click "Add Department" button */
        driver.findElement(AddDept).click();
        Thread.sleep(1000);
        /** passing random string as Department name */
        String randomDept = Long.toHexString(Double.doubleToLongBits(Math.random()));
        driver.findElement(DeptName).sendKeys(randomDept);
        System.out.println("Department name:" +randomDept);

        /** Click "ADD" Button */
        WebElement element = driver.findElement(Add_Button);
        Thread.sleep(1000);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
        System.out.println("New department  " + randomDept + " is created");
        Thread.sleep(2000);
        /**   After adding department ,Search the same dept & click on it  */
        driver.findElement(By.xpath("//table//tr[2]//div[@class='col-md-6']//a")).click();
        Thread.sleep(300);

    }



}
