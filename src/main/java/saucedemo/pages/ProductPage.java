package saucedemo.pages;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class ProductPage extends BasePage {

	public ProductPage(WebDriver driver) {
		super(driver);
		
	}
	
	//locators

	private By product_Names = By.className("inventory_item_name");
	private By addToCartBtn = By.xpath("//button[contains(text(),'Add to cart')]");
	private By removeBtn = By.xpath("//button[contains(text(),'Remove')]");
	private By cartIcon = By.xpath("//a[@class='shopping_cart_link']");
	private By cartBadge = By.className("shopping_cart_badge");
	private By filter_option = By.xpath("//select[@class='product_sort_container']");
	private By menubtn = By.id("react-burger-menu-btn");
	private By logout = By.id("logout_sidebar_link");
	

 
	//action methods
	
	public List<WebElement> productNames(){
		List<WebElement> productNames = driver.findElements(product_Names);
		return productNames;
		}
	
	public boolean productDetailsDisplayed() {
		boolean isDisplayed = false;
		List<WebElement> productNames = driver.findElements(product_Names);
		for(WebElement productName:productNames) {
			isDisplayed = productName.isDisplayed();
		}
		return isDisplayed;
	}
	
	public void addAllToCart() throws InterruptedException{
		List<WebElement> addToCartBtns = driver.findElements(addToCartBtn);
		for(WebElement addtocart:addToCartBtns) {
			addtocart.click();
			Thread.sleep(500);
			
		}
	}
	
	public void removeAllFromCart() throws InterruptedException {
		List<WebElement> removeFromCartBtns = driver.findElements(removeBtn);
		for(WebElement removeFromCart:removeFromCartBtns) {
			removeFromCart.click();
			Thread.sleep(500);
		}
	}

	    public int getCartCount() {
	        List<WebElement> badge = driver.findElements(cartBadge);
	        return badge.size() > 0 ? Integer.parseInt(badge.get(0).getText()) : 0;
	    }
	    
	    public String selectFilter() throws InterruptedException {
	    	WebElement filter = driver.findElement(filter_option);
	    	filter.click();
	    	Thread.sleep(1500);
	    	Select select = new Select(filter);
	    	select.selectByIndex(1);
	    	Thread.sleep(500); // optional small wait
	        filter = driver.findElement(filter_option);
	        select = new Select(filter);
	        
	        return select.getFirstSelectedOption().getText();
	    }
	    
	    public String productDetails() {
	    	List<WebElement> productDetails = driver.findElements(product_Names);
	    	String productName = productDetails.get(0).getText();
	    	productDetails.get(0).click();
	    	return productName;
	    }
	    
	    public void goToCart() {
	    	driver.findElement(cartIcon).click();
	    }
	    
	    public void menu() {
	    	driver.findElement(menubtn).click();
	    }
	    public void logout() {
	    	driver.findElement(logout).click();
	    }
	    

	    
	    
}
