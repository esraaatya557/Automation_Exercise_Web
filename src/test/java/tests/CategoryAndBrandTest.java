package tests;

import com.automationexercices.drivers.GUIDriver;
import com.automationexercices.pages.CategoryAndBrandPage;
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
public class CategoryAndBrandTest extends BaseTest {
    protected JsonReader testData;

    @Description("Verify category section")
    @Test(priority = 1)
    public void verifyCategorySidebar() {
        new CategoryAndBrandPage(driver).navigate()
                .verifyThatCategoriesAreVisibleOnLeftSideBar();

    }

    @Description("Verify products list matches the selected category")
    @Test(priority = 2)
    public void verifyCategoryProduct() {
        new CategoryAndBrandPage(driver).navigate()
                .clickOnCatergoryName(testData.getJsonData("category.name"))
                .clickOnSubCatergory(testData.getJsonData("category.subname"))
                .verifyThatCategoriesAreVisibleOnRightSideBar(testData.getJsonData("category.label"));

    }

    @Description("Verify Brands section")
    @Test(priority = 3)
    public void verifyBrandSidebar() {
        new CategoryAndBrandPage(driver).navigate()
                .verifyThatBrandsAreVisibleOnLeftSideBar();

    }

    @Description("Verify products list matches the selected Brand")
    @Test(priority = 4)
    public void verifyBrandProduct() {
        new CategoryAndBrandPage(driver).navigate()
                .clickOnBrandName(testData.getJsonData("brand.name"))
                .verifyThatBrandsAreVisibleOnRightSideBar(testData.getJsonData("brand.label"));

    }

    @Description("Verify Product Count Is correct")
    @Test(priority = 5)
    public void verifyProductCountIsCorrect() {
        new CategoryAndBrandPage(driver).navigate()
                .verifyThatProductCountIsCorrect(
                        testData.getJsonData("brand.name"),
                        testData.getJsonData("brand.number")
                );

    }

    @BeforeClass
    public void beforeClass() {
        testData = new JsonReader("category&brand-data");
    }

    @BeforeMethod
    public void Setup() {
        driver = new GUIDriver();
        new CategoryAndBrandPage(driver).navigate();

    }

    @AfterMethod
    public void TearDown() {
        driver.quitDriver();
    }
}
