package saucedemo.testclasses;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import saucedemo.pages.CheckOutPage;
import saucedemo.utils.ScreenshotUtil;

public class CheckOutPageTest extends BaseTest {

	private CheckOutPage checkout;
	
	@BeforeMethod
	public void init() {
		checkout = new CheckOutPage(driver);
	}
	
	@Test(priority=1)
	public void checkOutPageTest() {
		assertTrue(driver.getCurrentUrl().contains("checkout-step-one.html"));
        ScreenshotUtil.takeScreenshot(driver, this.getClass().getSimpleName(), "checkOutPageTest");

	}
	
	@Test(priority=2)
	public void checkOutWithoutDetailsTest() {
		String errorDisplayed = checkout.checkOutwithoutDetails();
		assertNotNull(errorDisplayed,"Error: First Name is required");
        ScreenshotUtil.takeScreenshot(driver, this.getClass().getSimpleName(), "checkOutWithoutDetailsTest");

	}
	
	@Test(priority=3)
	public void checkOutTest() {
		checkout.checkOutWithDetails();
		assertNotEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-one.html");
        ScreenshotUtil.takeScreenshot(driver, this.getClass().getSimpleName(), "checkOutTest");

	}
	
	@Test(priority=4)
	public void verifyTotalAmountTest() {
		String actualTotalAmount = checkout.totalAmount();
		assertEquals("Total: $140.34", actualTotalAmount);
        ScreenshotUtil.takeScreenshot(driver, this.getClass().getSimpleName(), "verifyTotalAmountTest");

	}
	
	@Test(priority=5,dependsOnMethods = "verifyTotalAmountTest")
	public void finishOrderingTest() {
		String orderMessage = checkout.finishOrdering();
		assertEquals("Thank you for your order!", orderMessage);
        ScreenshotUtil.takeScreenshot(driver, this.getClass().getSimpleName(), "finishOrderingTest");

	}
	
}
