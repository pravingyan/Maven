package ua.com.rozetka.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import ua.com.rozetka.utils.Browser;
import ua.com.rozetka.utils.Checker;
import ua.com.rozetka.utils.Logger;
import ua.com.rozetka.utils.TestData;

public abstract class BasePage {

    protected By searchInputBy = By.xpath("//input[@name='text']");
    protected By loginLinkBy = By.xpath("//a[@name='signin']");
    protected By emailInputBy = By.xpath("//input[@name='login']");
    protected By passwordInputBy = By.xpath("//input[@name='password']");
    protected By loginButtonBy = By.xpath("//button[@type='submit']");
    protected By signUpLinkBy = By.xpath("//div[@class='auth-f-signup']/a");
    protected By logOutLinkBy = By.xpath("//div[@class='m-user-signout']/a");
    protected By cartPopUpLinkBy = By.xpath("//div[@class='m-cart-full']/a");
    protected By socialBarCloseSignBy = By.xpath("//img[@class='popup-close-icon' and @height='8']");
    protected By emptyCartLinkBy = By.xpath("//div[@class='m-cart-empty']");

    public HomePage login(TestData data) {
        Logger.info(String.format("Logging in '%s' user with '%s' password.", data.getUserName(), data.getUserPassword()));
        Browser.findElement(loginLinkBy).click();
        Browser.findElement(emailInputBy).clear();
        Browser.findElement(emailInputBy).sendKeys(data.getUserEmail());
        Browser.findElement(passwordInputBy).clear();
        Browser.findElement(passwordInputBy).sendKeys(data.getUserPassword());
        Browser.findElement(loginButtonBy).click();
        closeSocialBar();
        Logger.info("Log in finished");
        return new HomePage();
    }

    public boolean isUserLoggedIn(String userName) {
        Logger.info(String.format("Verifying whether user '%s' is logged in...", userName));
        By userNameBy = By.xpath(String.format("//a[.='%s']", userName));
        Browser.waitForElementToBeDisplayed(userNameBy);
        return Browser.waitForElementToBeDisplayed(userNameBy);
    }

    public boolean isUserLoggedOut(String userName) {
        Logger.info(String.format("Verifying whether user %s' is logged in...", userName));
        By userNameBy = By.xpath(String.format("//a[.='%s']", userName));
        return Browser.waitForInvisibilityOfElement(userNameBy);
    }

    public RegistrationPage openRegistrationPage() {
        Logger.info("Opening registration page...");
        Browser.findElement(loginLinkBy).click();
        Browser.findElement(signUpLinkBy).click();
        return new RegistrationPage();
    }

    public CartPopUp openCartPopUp() {
        Logger.info("Opening cart pop-up");
        if(Browser.isElementDisplayed(cartPopUpLinkBy)) {
            Browser.findElement(cartPopUpLinkBy).click();
            return new CartPopUp();
        } else {
            Checker.failTest("Couldn't open cart pop-up.");
        }
        return null;
    }

    public boolean isCartEmpty() {
        return Browser.isElementDisplayed(emptyCartLinkBy);
    }

    public SearchResultsPage search(TestData data) {
        Logger.info("Doing search with query: " + data.getProductSearchQuery());
        Browser.findElement(searchInputBy).clear();
        Browser.findElement(searchInputBy).sendKeys(data.getProductSearchQuery());
        Browser.findElement(searchInputBy).sendKeys(Keys.ENTER);
        return new SearchResultsPage();
    }

    public HomePage logout() {
        Logger.info("Logging user out...");
        Browser.findElement(logOutLinkBy).click();
        Logger.info("Log out finished");
        return new HomePage();
    }

    public void closeSocialBar() {
        Logger.info("Invoking closeSocialBar()");
        if (Browser.waitForElementToBeDisplayed(socialBarCloseSignBy)) {
            Logger.info("Social bar is indeed displayed, closing it...");
            Browser.findElement(socialBarCloseSignBy).click();
            Browser.waitForInvisibilityOfElement(socialBarCloseSignBy);
            Logger.info("Social bar has been closed");
        }
    }

}
