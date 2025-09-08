package saucedemo.testclasses;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import saucedemo.pages.CheckOutPage;
import saucedemo.pages.ProductPage;
import saucedemo.utils.ScreenshotUtil;

public class CheckOutPageTest extends BaseTest {

	private CheckOutPage checkout;
	private ProductPage productpage;
	@BeforeMethod
	public void init() {
		checkout = new CheckOutPage(driver);
		productpage = new ProductPage(driver);
	}
	
	@Test(priority=11)
	public void checkOutPageTest() {
		assertTrue(driver.getCurrentUrl().contains("checkout-step-one.html"));
        ScreenshotUtil.takeScreenshot(driver, this.getClass().getSimpleName(), "checkOutPageTest");

	}
	
	@Test(priority=12)
	public void checkOutWithoutDetailsTest() throws InterruptedException {
		String errorDisplayed = checkout.checkOutwithoutDetails();
		assertNotNull(errorDisplayed,"Error: First Name is required");
		Thread.sleep(2000);
        ScreenshotUtil.takeScreenshot(driver, this.getClass().getSimpleName(), "checkOutWithoutDetailsTest");

	}
	
	@Test(priority=13)
	public void checkOutTest() {
		checkout.checkOutWithDetails();
		ScreenshotUtil.takeScreenshot(driver, this.getClass().getSimpleName(), "checkOutTest");
		checkout.clickContinue();
		assertNotEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-one.html");
       

	}
	
	@Test(priority=14)
	public void verifyTotalAmountTest() throws InterruptedException {
		String actualTotalAmount = checkout.totalAmount();
		ScreenshotUtil.takeScreenshot(driver, this.getClass().getSimpleName(), "verifyTotalAmountTest");
		Thread.sleep(2000);
		assertEquals("Total: $140.34", actualTotalAmount);
        

	}
	
	@Test(priority=15,dependsOnMethods = "verifyTotalAmountTest")
	public void finishOrderingTest() throws InterruptedException {
		String orderMessage = checkout.finishOrdering();
		Thread.sleep(2000);
		assertEquals("Thank you for your order!", orderMessage);
        ScreenshotUtil.takeScreenshot(driver, this.getClass().getSimpleName(), "finishOrderingTest");

	}
	
	@Test(priority=16)
	public void homepageTest() {
		checkout.returnHome();
		assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/inventory.html");
	}
	

    @Test(priority=17)
    public void logoutTest() throws InterruptedException {
    	productpage.menu();
    	productpage.logout();
    	Thread.sleep(2000);
    	assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
    }
    

	
}
