package com.automationexercices.pages;

import com.automationexercices.drivers.GUIDriver;
import com.automationexercices.utils.dataReader.PropertyReader;
import com.automationexercices.utils.logs.LogsManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class CategoryAndBrandPage {
    private final GUIDriver driver;
    private final String productPage = "/products";

    public CategoryAndBrandPage(GUIDriver driver) {
        this.driver = driver;

    }

    private final By categoryElement = By.xpath("//h2[.=\"Category\"]");
    private final By brandElement = By.xpath("//h2[.=\"Brands\"]");

    private By subCategoryLabel(String label) {
        return By.xpath("//h2[.='" + label + "']");
    }

    private By BrandLabel(String label) {
        return By.xpath(" //h2[.=\"" + label + "\"]");
    }

    private By categoryName(String categoryname) {
        return By.xpath("//a[@href='#" + categoryname + "']");
    }

    private By brandName(String brandame) {
        return By.xpath("//a[@href=\"/brand_products/" + brandame + "\"]");
    }

    private By productNum(String brandame) {
        return By.xpath("//a[@href=\"/brand_products/" + brandame + "\"] //span");
    }

    private By categoryProduct(String categoryproduct) {
        return By.xpath("//a[.='" + categoryproduct + "']");
    }


    @Step("Navigate to Products Page")
    public CategoryAndBrandPage navigate() {
        driver.browser().navigateTo(PropertyReader.getProperty("baseUrlWeb") + productPage);
        return this;
    }

    @Step("Click on Catergory Name")
    public CategoryAndBrandPage clickOnCatergoryName(String name) {
        driver.element().click(categoryName(name));
        return this;
    }


    @Step("Click on Sub Catergory")
    public CategoryAndBrandPage clickOnSubCatergory(String name) {
        driver.element().click(categoryProduct(name));
        return this;
    }

    @Step("verify Categories Are Visible OnLeft SideBar")
    public CategoryAndBrandPage verifyThatCategoriesAreVisibleOnLeftSideBar() {
        driver.verification().isElementVisible(categoryElement);
        return this;
    }

    @Step("Verify products list matches the selected Category")
    public CategoryAndBrandPage verifyThatCategoriesAreVisibleOnRightSideBar(String labelname) {
        driver.verification().isElementVisible(subCategoryLabel(labelname));
        return this;
    }

    @Step("Click on Brand Name")
    public CategoryAndBrandPage clickOnBrandName(String name) {
        driver.element().click(brandName(name));
        return this;
    }

    @Step("verify Brands Are Visible OnLeft SideBar")
    public CategoryAndBrandPage verifyThatBrandsAreVisibleOnLeftSideBar() {
        driver.verification().isElementVisible(brandElement);
        return this;
    }

    @Step("Verify products list matches the selected Brand")
    public CategoryAndBrandPage verifyThatBrandsAreVisibleOnRightSideBar(String labelname) {
        driver.verification().isElementVisible(BrandLabel((labelname)));
        return this;
    }

    @Step("Verify Product Count Is correct")
    public CategoryAndBrandPage verifyThatProductCountIsCorrect(String name, String productNum) {
        String actualNum = driver.element().getText(productNum(name));
        LogsManager.info("products Number is: " + actualNum);
        driver.verification().Equals(actualNum, productNum, "Product count dosen't match brand count!");
        return this;
    }


}
