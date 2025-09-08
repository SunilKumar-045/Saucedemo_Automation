package saucedemo.testclasses;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import saucedemo.pages.LoginPage;
import saucedemo.utils.ScreenshotUtil;

public class LoginPageTest extends BaseTest {
	
	private LoginPage loginpage;

	@BeforeMethod
	public void init() {
	    loginpage = new LoginPage(driver);
	}
	@AfterMethod
	public void close() {
	    driver.navigate().refresh();
	}

	@Test(priority=1)
    public void urlWorking() {
		 ScreenshotUtil.takeScreenshot(driver, this.getClass().getSimpleName(), "beforeLogin");
        String actual = loginpage.logo();
        assertEquals("Swag Labs", actual);
    }

    @Test(priority=2)
    public void inValidLogin() {
    	loginpage.setUserName("standard");
        loginpage.setPassWord("secret_sauce");
        loginpage.clickLogin();
        ScreenshotUtil.takeScreenshot(driver, this.getClass().getSimpleName(), "ValidLogin");
        String actualerror = loginpage.error();
        assertEquals("Epic sadface: Username and password do not match any user in this service", actualerror);
    }
    @Test(priority=3)
    public void lockedUser() {
    	loginpage.setUserName("locked_out_user");
        loginpage.setPassWord("secret_sauce");
        loginpage.clickLogin();
        ScreenshotUtil.takeScreenshot(driver, this.getClass().getSimpleName(), "lockeduser");
        assertNotNull(loginpage.error());
        
    }
    
    @Test(priority=4)
    public void validLogin() {
        loginpage.setUserName("standard_user");
        loginpage.setPassWord("secret_sauce");
        loginpage.clickLogin();
        ScreenshotUtil.takeScreenshot(driver, this.getClass().getSimpleName(), "afterLogin");
        String actualUrl = driver.getCurrentUrl();
        assertEquals("https://www.saucedemo.com/inventory.html", actualUrl);
    }
    
    
}
