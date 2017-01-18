package com.qauber.pagesresource;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by erikfriedlander on 12/18/16.
 */
public class Mailinator {
    private WebDriver driver;
    private static final String URL = "https://www.mailinator.com";

    public Mailinator(WebDriver driver) {
        this.driver = driver;
    }

    private WebElement getFrontPageEmailField() {
        return driver.findElement(By.xpath("//*[@id=\"inboxfield\"]"));
    }
    private WebElement getFrontPageGoButton() {
        return driver.findElement(By.xpath("//span/button"));
    }
    private WebElement getInboxEmail1() {
        return driver.findElement(By.xpath("//div[2]/div[5]/div"));
    }

    private WebElement getiFrameActivationLink() { //make sure to get link text... or find something that contains qabidder.net
        return driver.findElements(By.xpath("/html/body//a")).get(0);
    }

    public String getFirstLinkInFirstEmailInAccount(String emailAddressLocalPart) throws InterruptedException { //returns first link in first email in the account as a string.
        driver.get(URL);
        int waitTime = 5000;

        Thread.sleep(waitTime);

        getFrontPageEmailField().click();
        getFrontPageEmailField().sendKeys(emailAddressLocalPart);
        getFrontPageGoButton().click();
        Thread.sleep(waitTime);

        getInboxEmail1().click();
        Thread.sleep(waitTime);

        //Switch to iFrame
        driver.switchTo().frame("publicshowmaildivcontent");
//driver.findElements(By.xpath("/html/body/a")).get(0).getAttribute("href")
        String iFrameActivationLink = getiFrameActivationLink().getAttribute("href"); //return link as string
        System.out.println(iFrameActivationLink);
        driver.switchTo().parentFrame();
        return iFrameActivationLink; //return link as string

    }

}
