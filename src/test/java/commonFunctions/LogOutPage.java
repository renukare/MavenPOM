package commonFunctions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogOutPage {
	WebDriver driver;
	public LogOutPage(WebDriver driver)
	{
		this.driver=driver;
	}
	//define repository for logout
	@FindBy(id="welcome")
	WebElement objwelcome;
	@FindBy(linkText="Logout")
	WebElement objlogout;
	public void verifyLogout() throws Throwable
	{
		this.objwelcome.click();
		Thread.sleep(2000);
		this.objlogout.click();
	}

}
