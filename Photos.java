package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by jaishree on 4/6/17.
 */
public class Photos {

    private WebDriver driver;

    public Photos(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement Nextbtn() {
        return driver.findElement(By.xpath("//a[@ng-click='wizard.go(8)']"));
    }


}
