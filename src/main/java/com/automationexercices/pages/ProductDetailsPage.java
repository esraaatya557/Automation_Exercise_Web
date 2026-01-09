package com.automationexercices.pages;

import com.automationexercices.drivers.GUIDriver;
import com.automationexercices.utils.dataReader.PropertyReader;
import com.automationexercices.utils.logs.LogsManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class ProductDetailsPage {
    protected GUIDriver driver;
    protected String productDetailsEndpoint = "product_details/1";

    public ProductDetailsPage(GUIDriver driver) {
        this.driver = driver;
    }

    private final By productName = By.cssSelector(".product-information >h2");
    private final By productPrice = By.cssSelector(".product-information >span >span");
    private final By name = By.id("name");
    private final By email = By.id("email");
    private final By reviewTextArea = By.id("review");
    private final By reviewButton = By.id("button-review");
    private final By reviewMsg = By.cssSelector("#review-section span");

    public ProductDetailsPage navigate() {
        driver.browser().navigateTo(PropertyReader.getProperty("baseUrlWeb") + productDetailsEndpoint);
        return this;
    }

    @Step("write review on product")
    public ProductDetailsPage addReview(String name, String email, String review) {
        driver.element().type(this.name, name);
        driver.element().type(this.email, email);
        driver.element().type(this.reviewTextArea, review);
        driver.element().click(this.reviewButton);
        return this;
    }

    @Step("verify product details")
    public ProductDetailsPage verifyProductDetails(String pName, String pPrice) {
        String actualProductName = driver.element().getText(productName);
        String actualProductPrice = driver.element().getText(productPrice);
        LogsManager.info("actual product name:", actualProductName, "actual price:", actualProductPrice);
        driver.validation().Equals(actualProductName, pName, "Product Name Verification Failed");
        driver.validation().Equals(actualProductPrice, pPrice, "Product Price Verification Failed");
        return this;
    }

    @Step("verify review message")
    public ProductDetailsPage verifyReviewMsg(String msg) {
        String actualMsg = driver.element().getText(reviewMsg);
        LogsManager.info("actual msg:", actualMsg);
        driver.verification().Equals(actualMsg, msg, "Review Message Verification Failed");
        return this;
    }


}
