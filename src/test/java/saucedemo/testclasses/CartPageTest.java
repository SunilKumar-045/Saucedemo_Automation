package saucedemo.testclasses;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import saucedemo.pages.CartPage;
import saucedemo.pages.ProductPage;
import saucedemo.utils.ScreenshotUtil;

public class CartPageTest extends BaseTest{

	private CartPage cartpage;
	private ProductPage productpage;
	@BeforeMethod
	public void init() {
		cartpage = new CartPage(driver);
		productpage = new ProductPage(driver);
	}
	
	@Test(priority=1)
	public void isCartPage() {
		assertTrue(driver.getCurrentUrl().contains("cart.html"));
        ScreenshotUtil.takeScreenshot(driver, this.getClass().getSimpleName(), "isCartPage");

	}
	
	@Test(priority=2)
	public void checkCartWithEmpty() throws InterruptedException {
		cartpage.removeFromCart();
		 int itemCount = cartpage.getCartItemCount();
		    assertEquals(itemCount, 0, "Cart is not empty at start!");
		    cartpage.clickOnCheckOut();
		    Thread.sleep(2000);
		    String currentUrl = driver.getCurrentUrl();
		    assertFalse(currentUrl.contains("checkout-step-one.html"),
		            "BUG: Checkout is opening even when cart is empty!");
	        ScreenshotUtil.takeScreenshot(driver, this.getClass().getSimpleName(), "checkCartWithEmpty");

		    
	}
	
	@Test(priority=3)
	public void verifyAddedProducts() throws InterruptedException {
		driver.navigate().back();
		cartpage.continueShopping();
		productpage.addAllToCart();
		List<WebElement> addedProducts = cartpage.verifyAddedProducts();
		for(WebElement productAdded:addedProducts) {
			assertTrue(productAdded.isDisplayed());
		}
        ScreenshotUtil.takeScreenshot(driver, this.getClass().getSimpleName(), "verifyAddedProducts");

	}
	
	@Test(priority=4)
	public void clickCheckOut() {
		
		productpage.goToCart();
		cartpage.clickOnCheckOut();
		assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-one.html");
        ScreenshotUtil.takeScreenshot(driver, this.getClass().getSimpleName(), "clickCheckOut");

	}
	
	
	
		
}
