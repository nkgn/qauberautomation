package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by jaishree on 3/23/17.
 */
public class InviteRegularUsers {
    public WebDriver driver;

    public InviteRegularUsers(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement EmailField() {
        return driver.findElement(By.xpath("//div[@class='bootstrap-tagsinput']//input[@type='text']"));

    }

    public WebElement NextbuttonafterRegUsers() {
        return driver.findElement(By.xpath("//button[@ng-click='$ctrl.nextStep()']"));
    }

    public WebElement Donebutton() {
        return driver.findElement(By.xpath("//button[@ng-click='confirm()']"));
    }

}
