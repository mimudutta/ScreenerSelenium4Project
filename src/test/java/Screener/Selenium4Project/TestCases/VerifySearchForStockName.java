package Screener.Selenium4Project.TestCases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import Screener.Selenium4Project.Base.TestBase;

public class VerifySearchForStockName extends TestBase{

	public String PageTitle ="Stock Screener and fundamental analysis tool for Indian stocks - Screener";
	
	@BeforeMethod
	public void setup() {
		initialization();
	}
	
	@AfterMethod
	public void Browser() {
		tearDown();
	}
	
	@Test
	public void verifyTitle() throws InterruptedException {
		String titleName=driver.getTitle();
		System.out.println("Title: "+ titleName);
		Assert.assertEquals(PageTitle, titleName, "Both the strings are not equal");
	}

	
	//@Test(dependsOnMethods = {"verifyTitle"})
	public void verifySearchResult() {
		String titleName=driver.getTitle();
		System.out.println("Title: "+ titleName);
		Assert.assertEquals(PageTitle, titleName, "Both the strings are not equal");
	}
}
