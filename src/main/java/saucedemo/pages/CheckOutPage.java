package saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class CheckOutPage extends BasePage{

	//constructor
	public CheckOutPage(WebDriver driver) {
		super(driver);
	}

	//locators
	private By firstName_field = By.id("first-name");
	private By lastName_field = By.id("last-name");
	private By zipCode_field = By.id("postal-code");
	private By continuebtn = By.id("continue");
	private By errorBox = By.className("error-message-container");
	private By totalAmount = By.xpath("//div[@data-test='total-label']");
	private By finishbtn = By.id("finish");
	private By orderMessage = By.cssSelector(".complete-header");
	
	//actions methods
	public String checkOutwithoutDetails() {
		 driver.findElement(continuebtn).click();
		 return driver.findElement(errorBox).getText();
		 
	}
	
	public  void checkOutWithDetails() {
		driver.findElement(firstName_field).sendKeys("Sunil");
		driver.findElement(lastName_field).sendKeys("Chakali");
		driver.findElement(zipCode_field).sendKeys("045045");
		driver.findElement(continuebtn).click();
	}
	
	public String totalAmount() {
		String total = driver.findElement(totalAmount).getText();
		return total;
	}
	
	public String finishOrdering() {
		driver.findElement(finishbtn).click();
		return driver.findElement(orderMessage).getText();
	}
	
	
	
}
