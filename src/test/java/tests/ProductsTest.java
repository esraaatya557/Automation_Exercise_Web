package tests;

import com.automationexercices.drivers.GUIDriver;
import com.automationexercices.pages.ProductsPage;
import com.automationexercices.utils.dataReader.JsonReader;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("Automation Exercise")
@Story("Products Management")
@Severity(SeverityLevel.CRITICAL)
@Owner("Esraa Atya")
public class ProductsTest extends BaseTest {

    protected JsonReader testData;
    protected JsonReader testPDData;


    @Description("Search for a product and validate its details")
    @Test
    public void searchForProductWithoutLogin() {
        new ProductsPage(driver).navigate()
                .searchProduct(testData.getJsonData("searchedProduct.name"))
                .validateProductDetails(
                        testData.getJsonData("searchedProduct.name")
                        , testData.getJsonData("searchedProduct.price"));

    }

    @Description("Add a product to the cart without logging in")
    @Test
    public void addProductToCartWithoutLogin() {
        new ProductsPage(driver).navigate()
                .clickOnAddToCart(testData.getJsonData("searchedProduct.name"))
                .validateItemAddedLabel(testData.getJsonData("messages.cartAdded"))
                .clickOnContinueShopping();

    }

    @Description("Verify Product Details")
    @Test
    public void verifyProductDetailsTCWithoutLogin() {
        new ProductsPage(driver)
                .navigate()
                .clickOnViewProduct(testPDData.getJsonData("product.name"))
                .verifyProductDetails(testPDData.getJsonData("product.name"), testPDData.getJsonData("product.price"));
    }

    @Description("Verify Review Message")
    @Test
    public void verifyReviewMessageTCWithoutLogin() {
        new ProductsPage(driver)
                .navigate()
                .clickOnViewProduct(testPDData.getJsonData("product.name"))
                .addReview(testPDData.getJsonData("review.name"),
                        testPDData.getJsonData("review.email"),
                        testPDData.getJsonData("review.review"))
                .verifyReviewMsg(testPDData.getJsonData("messages.review"));
    }

    @BeforeClass
    protected void preCondition() {

        testData = new JsonReader("products-data");
        testPDData = new JsonReader("product-details-data");
    }

    @BeforeMethod
    public void Setup() {
        driver = new GUIDriver();
        new ProductsPage(driver).navigate();
       

    }

    @AfterMethod
    public void TearDown() {
        driver.quitDriver();
    }

}
