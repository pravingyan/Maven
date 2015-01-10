package ua.com.rozetka.pages;

import org.openqa.selenium.By;
import ua.com.rozetka.utils.Browser;
import ua.com.rozetka.utils.Logger;
import ua.com.rozetka.utils.TestData;

public class AccountPage extends BasePage {

    private String userNameXpath = "//div[.='%s']";

    public boolean isUserRegistered(TestData data) {
        Logger.info("Verifying whether user name is displayed...");
        By userNameBy = By.xpath(String.format(userNameXpath, data.getUserName()));
        if(Browser.waitForElementToBeDisplayed(userNameBy)) {
            return true;
        } else {
            Logger.logError("Couldn't find name of registered user.");
            return false;
        }
    }

}
