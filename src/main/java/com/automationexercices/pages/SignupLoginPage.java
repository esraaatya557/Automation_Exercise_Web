package com.automationexercices.pages;

import com.automationexercices.drivers.GUIDriver;
import com.automationexercices.pages.components.NavigationBarComponent;
import com.automationexercices.utils.dataReader.PropertyReader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class SignupLoginPage {
    private final GUIDriver driver;
    private final String signuploginEndPoint = "/login";
    public NavigationBarComponent navigationBar;

    public SignupLoginPage(GUIDriver driver) {
        this.driver = driver;
        this.navigationBar = new NavigationBarComponent(driver);

    }

    private final By loginEmail = By.xpath("//input[@data-qa=\"login-email\"]");
    private final By loginPassward = By.xpath("//input[@data-qa=\"login-password\"]");
    private final By loginButton = By.xpath("//button[@data-qa=\"login-button\"]");
    private final By signupName = By.xpath("//input[@data-qa=\"signup-name\"]");
    private final By signupEmail = By.xpath("//input[@data-qa=\"signup-email\"]");
    private final By signupButton = By.xpath("//button[@data-qa=\"signup-button\"]");
    private final By signuoLabel = By.xpath("//h2[.=\"New User Signup!\"]");
    private final By loginError = By.cssSelector(".login-form  p");
    private final By signupError = By.cssSelector(".signup-form p");


    @Step("Navigate to SignUP Login Page")
    public SignupLoginPage navigate() {
        driver.browser().navigateTo(PropertyReader.getProperty("baseUrlWeb") + signuploginEndPoint);
        return this;
    }

    @Step("Enter name {Email} in login field")
    public SignupLoginPage enterLoginEmail(String email) {
        driver.element().type(loginEmail, email);
        return this;
    }

    @Step("Enter name {passward} in passward field")
    public SignupLoginPage enterLoginPassward(String passward) {
        driver.element().type(loginPassward, passward);
        return this;
    }

    @Step("click on login button")
    public SignupLoginPage clickLoginButton() {
        driver.element().click(loginButton);
        return this;
    }


    @Step("Enter name {name} in signup field")
    public SignupLoginPage enterSignupName(String name) {
        driver.element().type(signupName, name);
        return this;
    }

    @Step("Enter name {email} in email field")
    public SignupLoginPage enterSignupEmail(String email) {
        driver.element().type(signupEmail, email);
        return this;
    }

    @Step("click on signup button")
    public SignupLoginPage clickSignupButton() {
        driver.element().click(signupButton);
        return this;
    }

    @Step("Verify new user signup visible")
    public SignupLoginPage verifySignupVisible() {
        driver.verification().isElementVisible(signuoLabel);
        return this;
    }

    @Step("Verify login error message {errorExpected}")
    public SignupLoginPage verifyLoginError(String errorExpected) {
        String actualError = driver.element().getText(loginError);
        driver.verification().Equals(actualError, errorExpected, "Login error message is not as expected");
        return this;
    }

    @Step("Verify signup error message {errorExpected}")
    public SignupLoginPage verifysignupError(String errorExpected) {
        String actualError = driver.element().getText(signupError);
        driver.verification().Equals(actualError, errorExpected, "signup error message is not as expected");
        return this;
    }


}
