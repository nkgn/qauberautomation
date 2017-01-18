package com.qauber.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;


// Created by Denys_G on 12/16/2016.
// Modified by Max on 12/19/2016.
// modified by Jing on 12/29/2016.

public class ProfilePanel {
    WebDriver driver;

    public ProfilePanel(WebDriver driver){this.driver = driver;}

    public WebElement userButton() {
        return driver.findElement(By.cssSelector(".icon-user.fa-lg"));
    }
    public WebElement settinsButton() {
        return driver.findElement(By.cssSelector(".icon-equalizer.fa-lg"));
    }
    public WebElement editProfileButton() {
        return driver.findElement(By.cssSelector(".btn.btn-purple.btn-sm"));
    }
    public WebElement mailtoEmail() {
        return driver.findElement(By.xpath("//a[contains(@ng-href,'mailto:')]"));
    }
    public WebElement callPhone() {
        return driver.findElement(By.xpath("//a[contains(@ng-href,'tell:')]"));
    }
    public WebElement editCompanyButton() {
        return driver.findElement(By.xpath("//button[@ng-click='$ctrl.editCompany()']"));
    }
    public WebElement logOutButton() {
        return driver.findElement(By.xpath("//button[@ng-click='app.logout()']"));
    }
    public WebElement subscriptionsText() {return driver.findElement(By.xpath("//*[@class='layout-fixed offsidebar-open']//small[text()='Subscriptions']"));}
    public WebElement settingsText() {return driver.findElement(By.xpath("//*[@class='layout-fixed offsidebar-open']//h3[text()='Settings']"));}
    public List<WebElement> entitiesList() {return driver.findElements(By.xpath("//*[@class='']//a/*[@class='ng-binding']"));}
    public WebElement entitiesName(int index) {return entitiesList().get(index-1);}
    public List<WebElement> hideEntitiesForNonSAU() {return driver.findElements(By.xpath("//*[@class='ng-hide']//a/*[@class='ng-binding']"));}

    public WebElement organizationLink(int number)
    {
        List<WebElement> list = driver.findElements(By.cssSelector(".badge.bg-info.pull-right.ng-binding"));
        return list.get(number-1);
    }
}
