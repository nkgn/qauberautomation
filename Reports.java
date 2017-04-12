package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by jaishree on 4/6/17.
 */
public class Reports {

    WebDriver driver;

    public Reports(WebDriver driver)
    {
        this.driver = driver;
    }

    public WebElement ViewReports() {
        return driver.findElement(By.xpath("//span[text()='Reports']"));
    }

    public WebElement ExportReportsListasPDF() {
        return driver.findElement(By.xpath("//button[@ng-click='downloadAsPdf()']//span[text()='Export Reports List as PDF']"));
    }


}
