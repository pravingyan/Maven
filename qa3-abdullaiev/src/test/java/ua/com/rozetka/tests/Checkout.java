package ua.com.rozetka.tests;

import ua.com.rozetka.utils.Checker;
import ua.com.rozetka.pages.CartPopUp;
import ua.com.rozetka.pages.CheckoutPage;
import ua.com.rozetka.pages.HomePage;
import ua.com.rozetka.pages.SearchResultsPage;
import org.testng.annotations.Test;

public class Checkout extends BaseTest {

    @Test
    public void checkoutPositive() {
        data = data.setRandomUser();

        HomePage homePage = new HomePage();
        CartPopUp cartPopUp;

        if (!homePage.isCartEmpty()) {
            cartPopUp = homePage.openCartPopUp();
            cartPopUp.clearCart();
            cartPopUp.close();
        }

        SearchResultsPage searchResultsPage = homePage.search(data);
        cartPopUp = searchResultsPage.addToCart(data);

        CheckoutPage checkoutPage = cartPopUp.proceedToCheckout();
        checkoutPage = checkoutPage.specifyBillingInfo(data);

        Checker.checkTrue(checkoutPage.isPlaceOrderButtonDisplayed());

        cartPopUp = checkoutPage.clickEditOrder();
        cartPopUp = cartPopUp.clearCart();
        cartPopUp.close();
    }

}
