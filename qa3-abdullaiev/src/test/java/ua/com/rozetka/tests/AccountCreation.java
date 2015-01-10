package ua.com.rozetka.tests;

import org.testng.annotations.Test;
import ua.com.rozetka.utils.Checker;
import ua.com.rozetka.pages.AccountPage;
import ua.com.rozetka.pages.HomePage;
import ua.com.rozetka.pages.RegistrationPage;

public class AccountCreation extends BaseTest {

    @Test
    public void accountCreationTest() {
        data = data.setRandomUser();
        RegistrationPage registrationPage = new HomePage().openRegistrationPage();
        AccountPage accountPage = registrationPage.registerUser(data);
        boolean isRegistered = accountPage.isUserRegistered(data);
        Checker.checkTrue(isRegistered, "User has not been registered.");
        accountPage.logout();
    }

}
