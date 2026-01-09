package tests;

import com.automationexercices.drivers.GUIDriver;
import com.automationexercices.drivers.WebDriverProvider;
import org.openqa.selenium.WebDriver;

public class BaseTest implements WebDriverProvider {
    protected GUIDriver driver;


    @Override
    public WebDriver getWebDriver() {

        return driver.get();
    }
}
