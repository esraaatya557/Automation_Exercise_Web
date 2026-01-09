package tests;

import com.automationexercices.drivers.GUIDriver;
import com.automationexercices.pages.SignupLoginPage;
import com.automationexercices.pages.SignupPage;
import com.automationexercices.pages.components.NavigationBarComponent;
import com.automationexercices.utils.TimeManager;
import com.automationexercices.utils.dataReader.JsonReader;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("Automation Exercise")
@Story("User Login")
@Severity(SeverityLevel.CRITICAL)
@Owner("Esraa Atya")

public class LoginTest extends BaseTest {
    protected JsonReader testData;
    protected String timestamp = TimeManager.getSimpleTimestamp();

    @Description("Verify user can login with valid credentials")
    @Test(priority = 1)
    public void ValidLoginTC() {

        new SignupLoginPage(driver).navigate()
                .enterSignupName(testData.getJsonData("name"))
                .enterSignupEmail(testData.getJsonData("email") + timestamp + "@gmail.com")
                .clickSignupButton();
        new SignupPage(driver).fillRegisterationFormWithMinimalData(
                        testData.getJsonData("password"),
                        testData.getJsonData("firstName"),
                        testData.getJsonData("lastName"))
                .clickCreateAccountButton()
                .verifyAccountCreated()
                .clickContinueButton()
                .clickOnLogoutButton()
                .enterLoginEmail(testData.getJsonData("email") + timestamp + "@gmail.com")
                .enterLoginPassward(testData.getJsonData("password"))
                .clickLoginButton()
                .navigationBar.verifyUserLabel("Logged in as " + testData.getJsonData("name"));


    }

    @Description("Verify user cannot login with invalid password")
    @Test(priority = 2)
    public void inValidLoginWithInvalidPasswordTC() {
        new SignupLoginPage(driver).navigate()
                .enterLoginEmail(testData.getJsonData("email") + timestamp + "@gmail.com")
                .enterLoginPassward("Passw0rd")
                .clickLoginButton()
                .verifyLoginError(testData.getJsonData("messages.error"));
    }

    @Description("Verify user cannot login with invalid email")
    @Test(priority = 3)
    public void inValidLoginWithInvalidEmailTC() {
        new SignupLoginPage(driver).navigate()
                .enterLoginEmail("testautomation" + "@gmail.com")
                .enterLoginPassward(testData.getJsonData("password"))
                .clickLoginButton()
                .verifyLoginError(testData.getJsonData("messages.error"));
    }

    @BeforeClass
    public void beforeClass() {
        testData = new JsonReader("login-data");
    }

    @BeforeMethod
    public void Setup() {
        driver = new GUIDriver();
        new NavigationBarComponent(driver).navigate();

    }

    @AfterMethod
    public void TearDown() {
        driver.quitDriver();
    }
}
