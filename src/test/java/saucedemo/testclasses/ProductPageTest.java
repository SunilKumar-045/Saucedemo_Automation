package saucedemo.testclasses;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import saucedemo.pages.ProductPage;
import saucedemo.utils.ScreenshotUtil;

public class ProductPageTest extends BaseTest {

	private ProductPage productpage ;
	
	@BeforeMethod
	public void init() {
	    productpage = new ProductPage(driver);
	    
	}
	
	
	@Test(priority=1)
	public void productDisplayed() {
		boolean displayed = productpage.productDetailsDisplayed();
		assertTrue(displayed);
        ScreenshotUtil.takeScreenshot(driver, this.getClass().getSimpleName(), "productDisplayed");
		
	}
	
	    @Test(priority = 2)  // TC013
	    public void filterTest() throws InterruptedException {
	    	String actual = productpage.selectFilter();
	    	String expected = "Name (Z to A)";
	    	Thread.sleep(2000);
	    	assertEquals(expected, actual);
	        ScreenshotUtil.takeScreenshot(driver, this.getClass().getSimpleName(), "filterTest");
	    	
	    }
	    @Test(priority = 3)  // TC012
	    public void addProductToCart() throws InterruptedException {
	        productpage.addAllToCart();
	        assertEquals(productpage.getCartCount(), 6);
	        ScreenshotUtil.takeScreenshot(driver, this.getClass().getSimpleName(), "addProductToCart");
	    }
	    @Test(priority = 4)
	    public void detailsTest() throws InterruptedException {
	    	String actual = productpage.productDetails();
	    	assertEquals(actual, "Test.allTheThings() T-Shirt (Red)");
	        ScreenshotUtil.takeScreenshot(driver, this.getClass().getSimpleName(), "detailsTest");
	    	Thread.sleep(1000);
	    	driver.navigate().back();
	    	
	    }
	    
	    @Test(priority = 5)  // TC013
	    public void removeProductFromCart() {
	        productpage.removeAllFromCart();
	        assertEquals(productpage.getCartCount(), 0);
	        ScreenshotUtil.takeScreenshot(driver, this.getClass().getSimpleName(), "removeProductFromCart");
	    }
	    
	    @Test(priority = 6)
	    public void goToCartTest() throws InterruptedException {
	    	productpage.addAllToCart();
	    	productpage.goToCart();
	    	Thread.sleep(1000);
	    	String actual = driver.getCurrentUrl();
	    	assertTrue("https://www.saucedemo.com/cart.html".equalsIgnoreCase(actual));
	        ScreenshotUtil.takeScreenshot(driver, this.getClass().getSimpleName(), "goToCartTest");
	    }
   

}
