package ua.com.rozetka.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ua.com.rozetka.utils.Browser;
import ua.com.rozetka.utils.Logger;

public class CartPopUp extends BasePage {

    public By cartPopUpBy = By.id("cart-popup");
    private By proceedToCheckoutButtonBy = By.id("popup-checkout");
    private By deleteProductImgBy = By.xpath("//a[@name='delete']/img");
    private By closeSignBy = By.xpath("//a[@class='popup-close']/img");

    public CheckoutPage proceedToCheckout() {
        Logger.info("Opening Checkout page...");
        if(Browser.isElementDisplayed(cartPopUpBy)) {
            clickCheckoutButton();
        } else {
            openCartPopUp();
            clickCheckoutButton();
        }
        return new CheckoutPage();
    }

    public void clickCheckoutButton() {
        Logger.info("Clicking checkout button...");
        Browser.findElement(proceedToCheckoutButtonBy).click();
    }

    public CartPopUp clearCart() {
        Logger.info("Clearing cart...");
        while(Browser.isElementDisplayed(deleteProductImgBy)) {
            Browser.findElement(deleteProductImgBy).click();
        }
        Logger.info("Clear cart finished");
        return this;
    }

    public void close() {
        Logger.info("Closing cart pop up...");
        if (Browser.isElementDisplayed(closeSignBy)) {
            Logger.info("Cart pop up is indeed displayed, closing it...");
            Browser.findElement(closeSignBy).click();
        } else {
            Logger.info("Cart pop up was not displayed");
        }
    }

}
