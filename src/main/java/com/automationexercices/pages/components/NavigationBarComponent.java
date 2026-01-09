package com.automationexercices.pages.components;

import com.automationexercices.drivers.GUIDriver;
import com.automationexercices.pages.*;
import com.automationexercices.utils.dataReader.PropertyReader;
import com.automationexercices.utils.logs.LogsManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class NavigationBarComponent {
    private final GUIDriver driver;

    public NavigationBarComponent(GUIDriver driver) {

        this.driver = driver;
    }

    private final By homeButton = By.xpath("//a[.=' Home']");
    private final By productsButton = By.cssSelector("a[href='/products']");
    private final By cartButton = By.xpath("//a[.=' Cart']");
    private final By signupLoginButton = By.xpath("//a[.=' Signup / Login']");
    private final By deleteAccountButton = By.xpath("//a[.=' Delete Account']");
    private final By contactUsButton = By.xpath("//a[.=' Contact us']");
    private final By homePageLabel = By.cssSelector("h1 > span");
    private final By userLabel = By.xpath("//a[text()=\" Logged in as \"]");
    private final By logoutButton = By.xpath("//a[.=' Logout']");


    @Step("Navigate to Home Page")
    public NavigationBarComponent navigate() {
        driver.browser().navigateTo(PropertyReader.getProperty("baseUrlWeb"));
        return this;
    }

    @Step("Click on Home Button")
    public NavigationBarComponent clickOnHomeButton() {
        driver.element().click(homeButton);
        return this;
    }

    @Step("Click on Products Button")
    public ProductsPage clickOnProductsButton() {
        driver.element().click(productsButton);
        return new ProductsPage(driver);
    }

    @Step("Click on Cart Button")
    public CartPage clickOnCartButton() {
        driver.element().click(cartButton);
        return new CartPage(driver);
    }

    @Step("Click on Signup/Login Button")
    public SignupLoginPage clickOnSignupLoginButton() {
        driver.element().click(signupLoginButton);
        return new SignupLoginPage(driver);
    }

    @Step("Click on Logout Button")
    public SignupLoginPage clickOnLogoutButton() {
        driver.element().click(logoutButton);
        return new SignupLoginPage(driver);
    }


    @Step("Click on Delete Account Button")
    public DeleteAccountPage clickOnDeleteAccountButton() {
        driver.element().click(deleteAccountButton);
        return new DeleteAccountPage(driver);
    }

    @Step("Click on ContactUs Button")
    public ContactUsPage clickOnContactUsButton() {
        driver.element().click(contactUsButton);
        return new ContactUsPage(driver);
    }


    @Step("Verify Home Page Label")
    public NavigationBarComponent verifyHomePageLabel() {
        driver.verification().isElementVisible(homePageLabel);
        return this;
    }

    @Step("Verify User Label")
    public NavigationBarComponent verifyUserLabel(String expectedname) {
        String actualname = driver.element().getText(userLabel);
        LogsManager.info("Verify User Label: " + actualname);
        driver.verification().Equals(actualname, expectedname, "User label does not match. Expected: " + expectedname + ", Actual: " + actualname);
        return this;

    }


}