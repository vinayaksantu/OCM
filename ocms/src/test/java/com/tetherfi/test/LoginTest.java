package com.tetherfi.test;

import com.tetherfi.pages.HomePage;
import com.tetherfi.utility.PageFactory;
import com.tetherfi.utility.Screenshot;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class LoginTest extends BaseTest {
    @Test
    public void VerifySuccessfulLogin() {
        HomePage homePage = PageFactory.createPageInstance(driver, HomePage.class);
        Assert.assertTrue(homePage.verifyHomePageTitle(), "Home page title assertion failed");
        Assert.assertTrue(homePage.verifyWelcomeMsg(), "Welcome msg assertion failed");
    }
    @AfterMethod
    public void afterEachMethod(Method method) {
    }
}
