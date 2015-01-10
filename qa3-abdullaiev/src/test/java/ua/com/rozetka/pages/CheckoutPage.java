package ua.com.rozetka.pages;

import org.openqa.selenium.By;
import ua.com.rozetka.utils.Browser;
import ua.com.rozetka.utils.Logger;
import ua.com.rozetka.utils.TestData;
import ua.com.rozetka.utils.TimeHelper;

public class CheckoutPage extends BasePage {

    public By placeOrderButtonBy = By.id("make-order");
    private By nameInputBy = By.id("reciever_name");
    private By kyivCityLinkBy = By.xpath("//a[@locality_id='1']");
    private By phoneInputBy = By.id("reciever_phone");
    private By emailInputBy = By.id("reciever_email");
    private By nextStepButtonBy = By.xpath("//button[@class='btn-link-i' and @tabindex='6']");
    private By editOrderLinkBy = By.xpath("(//a[@name='edit'])[2]");

    public CheckoutPage specifyBillingInfo(TestData data) {
        Logger.info(String.format("Specifying Billing: %s %s", data.getUserName(), data.getPhoneNumber()));
        Browser.findElement(nameInputBy).clear();
        Browser.findElement(nameInputBy).sendKeys(data.getUserName());
        Browser.findElement(kyivCityLinkBy).click();
        Browser.findElement(phoneInputBy).clear();
        Browser.findElement(phoneInputBy).sendKeys(data.getPhoneNumber());
        Browser.findElement(emailInputBy).clear();
        Browser.findElement(emailInputBy).sendKeys(data.getUserEmail());
        //Following delay is needed because WebDriver finds Next Step Button visible when it is not yet
        TimeHelper.delay(3);
        Browser.waitForElementToBeClickable(Browser.findElement(nextStepButtonBy)).click();
        Logger.info("Specify Billing Info finish");
        return this;
    }

    public CartPopUp clickEditOrder() {
        Logger.info("Clicking edit order link...");
        Browser.findElement(editOrderLinkBy).click();
        return new CartPopUp();
    }

    public boolean isPlaceOrderButtonDisplayed() {
        Logger.info("Verifying if Place Order button displayed...");
        return Browser.waitForElementToBeClickable(Browser.findElement(placeOrderButtonBy)).isDisplayed();
    }
}
