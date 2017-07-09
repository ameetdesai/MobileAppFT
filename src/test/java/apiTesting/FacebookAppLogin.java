package apiTesting;
import java.net.MalformedURLException;
import java.net.URL;

import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
/*
 * Author(s) : 
 * Functionality: Facebook login test to validate the login functionality on the page
 * Comments : To improve more readability of the code I will put the xpath in a separate file.
 *            All the parameters and the hardcode vaues can go in a separate file.
 *            Test data like username password can be put in a testdata file.
 *            
 */

public class FacebookAppLogin {

	static IOSDriver iOSDriver;

//	Create and instantiate the driver
	@BeforeTest
	public void setup() throws MalformedURLException{
		DesiredCapabilities desiredCap= new DesiredCapabilities();
		desiredCap.setCapability("deviceName", "iOSDevice");
		iOSDriver= new IOSDriver(new URL("http://127.0.0.1:4729/wd/hub"),desiredCap);
	}

	
//	Validate invalid login with the error message displayed
	@Test
	public void testInvalidLogin() {
		iOSDriver.findElement(By.name("Log in with email")).click();
		iOSDriver.findElement(By.xpath("UIATableCell[1]/UIATextField")).sendKeys("sampleidforapp@gmail.com");
		iOSDriver.findElement(By.xpath("UIAsecureTextField")).sendKeys("somepasswordforid");
		iOSDriver.findElement(By.name("Log In")).click();
		MobileElement alertDialog= (MobileElement)(new WebDriverWait(iOSDriver,15)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//UIAAlert")));
		Assert.assertEquals(alertDialog.getText(), "Invalid Email Address");	
	}
	
//	Validate valid login and verify the logged in screen
	@Test
	public void testValidLogin() {
		iOSDriver.findElement(By.name("Log in with email")).click();
		iOSDriver.findElement(By.xpath("UIATableCell[1]/UIATextField")).sendKeys("sampleidforapp@gmail.com");
		iOSDriver.findElement(By.xpath("UIAsecureTextField")).sendKeys("somepasswordforid");
		iOSDriver.findElement(By.name("Log In")).click();
		String pageTitle;
		pageTitle=iOSDriver.getTitle();
		Assert.assertEquals(pageTitle, "Facebook Login page");
	}
	
	
	@AfterTest
	public void teardown(){
		iOSDriver.quit();
	}	
	
}
