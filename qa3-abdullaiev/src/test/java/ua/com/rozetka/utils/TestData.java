package ua.com.rozetka.utils;


public class TestData {
    private String userName;
    private String userEmail;
    private String userPassword;
    private String phoneNumber;
    private String productSearchQuery;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phone) {
        this.phoneNumber = phone;
    }

    public String getProductSearchQuery() {
        return productSearchQuery;
    }

    public void setProductSearchQuery(String searchQuery) {
        this.productSearchQuery = searchQuery;
    }

    public TestData setRandomUser() {
        this.setUserName(String.format("%s %s",
                StringHelper.generateRandomString(4, 10),
                StringHelper.generateRandomString(4, 10)));
        this.setUserEmail(StringHelper.generateRandomEmail());
        this.setUserPassword(StringHelper.generateRandomString(8, 16));
        return this;
    }
}
