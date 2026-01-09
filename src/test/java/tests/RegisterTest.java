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
@Story("User Registration")
@Severity(SeverityLevel.CRITICAL)
@Owner("Esraa Atya")

public class RegisterTest extends BaseTest {
    protected String timestamp = TimeManager.getSimpleTimestamp();
    protected JsonReader testData;

    @Description("Verify user can sign up with valid data")
    @Test(priority = 1)
    public void ValidRegisterTC() {
        new SignupLoginPage(driver).navigate()
                .enterSignupName(testData.getJsonData("name"))
                .enterSignupEmail(testData.getJsonData("email") + timestamp + "@gmail.com")
                .clickSignupButton();
        new SignupPage(driver)
                .fillRegisterationForm(
                        testData.getJsonData("titleFemale"),
                        testData.getJsonData("password"),
                        testData.getJsonData("day"),
                        testData.getJsonData("month"),
                        testData.getJsonData("year"),
                        testData.getJsonData("firstName"),
                        testData.getJsonData("lastName"),
                        testData.getJsonData("companyName"),
                        testData.getJsonData("address1"),
                        testData.getJsonData("address2"),
                        testData.getJsonData("country"),
                        testData.getJsonData("state"),
                        testData.getJsonData("city"),
                        testData.getJsonData("zipcode"),
                        testData.getJsonData("mobileNumber")
                )
                .clickCreateAccountButton()
                .verifyAccountCreated()
                .clickContinueButton();


    }

    @Description("Verify user can sign up with invalid data")
    @Test(priority = 2)
    public void InValidRegisterTC() {
        new SignupLoginPage(driver).navigate()
                .enterSignupName(testData.getJsonData("name"))
                .enterSignupEmail(testData.getJsonData("email") + timestamp + "@gmail.com")
                .clickSignupButton()
                .verifysignupError(testData.getJsonData("messages.error"));


    }


    @BeforeClass
    public void beforeClass() {
        testData = new JsonReader("register-data");
    }

    @BeforeMethod
    public void Setup() {
        driver = new GUIDriver();
        new NavigationBarComponent(driver).navigate();
        //  driver.browser().closeExtensionTab();

    }

    @AfterMethod
    public void TearDown() {
        driver.quitDriver();
    }
}
