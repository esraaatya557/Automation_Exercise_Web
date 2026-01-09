package tests;

import com.automationexercices.drivers.GUIDriver;
import com.automationexercices.pages.ProductsPage;
import com.automationexercices.pages.components.NavigationBarComponent;
import com.automationexercices.utils.dataReader.JsonReader;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("Cart Management")
@Story("Cart Details")
@Severity(SeverityLevel.CRITICAL)
@Owner("Esraa Atya")

public class CartTest extends BaseTest {
    protected JsonReader testData;
    protected JsonReader reader;


    @Description("Verify Product Details On Cart Without Login")
    @Test()
    public void verifyProductDetailsOnCartWithoutLogin() {
        new ProductsPage(driver)
                .navigate()
                .clickOnAddToCart(testData.getJsonData("product.name"))
                .validateItemAddedLabel(testData.getJsonData("messages.cartAdded"))
                .clickOnViewCart()
                .verifyProductDetailsOnCart(
                        testData.getJsonData("product.name"),
                        testData.getJsonData("product.price"),
                        testData.getJsonData("product.quantity"),
                        testData.getJsonData("product.total")
                );
    }

    @Description("Verify More than one Product On Cart Without Login")
    @Test()
    public void verifyMoreThanOneProductOnCartWithoutLogin() {
        new ProductsPage(driver)
                .navigate()
                .clickOnAddToCart(testData.getJsonData("product1.name"))
                .clickOnContinueShopping()
                .clickOnAddToCart(testData.getJsonData("product2.name"))
                .clickOnContinueShopping()
                .clickOnAddToCart(testData.getJsonData("product1.name"))
                .clickOnViewCart()
                .verifyProductDetailsOnCart(
                        testData.getJsonData("product1.name"),
                        testData.getJsonData("product1.price"),
                        testData.getJsonData("product1.quantity"),
                        testData.getJsonData("product1.total"))
                .verifyProductDetailsOnCart(
                        testData.getJsonData("product2.name"),
                        testData.getJsonData("product2.price"),
                        testData.getJsonData("product2.quantity"),
                        testData.getJsonData("product2.total"));
    }

    @Description
    @Test()
    public void removeProductFromCart() {
        new ProductsPage(driver).navigate()
                .clickOnAddToCart(testData.getJsonData("product.name"))
                .clickOnViewCart()
                .removeProduct(testData.getJsonData("product.name"))
                .verifyCartIsEmpty();
    }

    @BeforeClass
    protected void preCondition() {

        testData = new JsonReader("cart-data");
        reader = new JsonReader("login-data");

    }

    @BeforeMethod
    public void setUp() {
        driver = new GUIDriver();
        new NavigationBarComponent(driver).navigate();

    }

    @AfterMethod
    public void tearDown() {
        driver.quitDriver();
    }
}
