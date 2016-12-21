package com.qauber.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by erikfriedlander on 12/16/16.
 */
public class Entities {

    WebDriver driver;
    WebElement element;

    public Entities(WebDriver driver) {
        this.driver = driver;
    }

//    public WebElement entityList() {
//        return entityList("Best");
//    }

//    //this is totally broken, don't use it
//    public WebElement entityList(String textTest) {
////        return driver.findElement(By.xpath("//tr[@class='ng-scope']/td[@class='pl-lg']/td[contains (text(), textTest]"));
//      return WebElement driver.findElement(By.xpath("//tr[@class='ng-scope']/td[@class='pl-lg']"));
//
//    }

    //Create all web elements in Entities page

    // Web element for "Add organization"
    public WebElement addOrganizationButton(){
        element = driver.findElement(By.xpath("//section/div/h3/button"));
        return element;
    }

    // Web elements list for "Info" organization
    public List<WebElement> organizationList(){
        List<WebElement> elements = driver.findElements(By.cssSelector("td.pl-lg > a.ng-binding"));
        return elements;
    }


    // Web element for "Info" organization
    public WebElement organizationInfo(int rowindex){
        element = driver.findElement(By.xpath("//section/div/div/div/div/table/tbody/tr[" + rowindex + "]/td[1]/a"));
        return element;
    }

    // Web element for "Info" logo image
    public WebElement organizationLogo(int rowindex){
        element = driver.findElement(By.xpath("//section/div/div/div/div/table/tbody/tr[" + rowindex + "]/td[1]/div/img"));
        return element;
    }

    // Web element for "Action" assign permission
    public WebElement assignPermissionButton(int rowindex){
        element = driver.findElement(By.xpath("//section/div/div/div/div/table/tbody/tr[" + rowindex + "]/td[2]/button[1]"));
        return element;
    }

    // Web element for "Action" edit organization
    public WebElement editOrganizationButton(int rowindex){
        element = driver.findElement(By.xpath("//section/div/div/div/div/table/tbody/tr[" + rowindex + "]/td[2]/button[2]"));
        return element;
    }

    //Orange second button
    public WebElement orangeLastEditButton(){
        return driver.findElement(By.xpath("(.//button[3])[last()]"));

        //return driver.findElement(By.xpath("(.//button[@title='Finish creating the organization'])[last()]"));
    }

    // Web element for "Action" finish create an organization
    public WebElement finishCreateOrganizationButton(int rowindex){
        element = driver.findElement(By.xpath("//section/div/div/div/div/table/tbody/tr[" + rowindex + "]/td[2]/button[3]"));
        return element;
    }

    // Web element for "Action" delete an organization
    public WebElement deleteOrganizationButton(int rowindex){
        element = driver.findElement(By.xpath("//section/div/div/div/div/table/tbody/tr[" + rowindex + "]/td[2]/button[4]"));
        return element;
    }


}
