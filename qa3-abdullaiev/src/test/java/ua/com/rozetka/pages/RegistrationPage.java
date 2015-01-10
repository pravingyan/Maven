package ua.com.rozetka.pages;

import org.openqa.selenium.By;
import ua.com.rozetka.utils.Browser;
import ua.com.rozetka.utils.TestData;
import ua.com.rozetka.utils.Logger;

public class RegistrationPage extends BasePage {
    private By nameInputBy = By.xpath("//input[@name='title']");
    private By emailInputBy = By.xpath("//input[@name='email']");
    private By passwordInputBy = By.xpath("//input[@name='password']");
    private By signUpLinkBy = By.xpath("//button[@class='button-css-green']");
    private By ajaxCircleBy = By.xpath("//img[@alt='AJAX process']");

    public AccountPage registerUser(TestData data) {
        Logger.info(String.format("Registering '%s' user with '%s' email and '%s' password",
                data.getUserName(),
                data.getUserEmail(),
                data.getUserPassword()));

        Browser.findElement(nameInputBy).clear();
        Browser.findElement(nameInputBy).sendKeys(data.getUserName());

        Browser.findElement(emailInputBy).clear();
        Browser.findElement(emailInputBy).sendKeys(data.getUserEmail());

        Browser.findElement(passwordInputBy).clear();
        Browser.findElement(passwordInputBy).sendKeys(data.getUserPassword());

        Browser.findElement(signUpLinkBy).click();

        Browser.waitForElementToBeDisplayed(ajaxCircleBy);
        Browser.waitForInvisibilityOfElement(ajaxCircleBy);

        closeSocialBar();
        Logger.info("Register random user finish");
        return new AccountPage();
    }

}
