package commonFunctions;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {
	//define repository for login
	@FindBy(name="txtUsername")
	WebElement objuser;
	@FindBy(name = "txtPassword")
	WebElement objpass;
	@FindBy(xpath = "//input[@id='btnLogin']")
	WebElement loginbtn;
	public void verifyLogin(String username, String password) throws Throwable
	{
		objuser.sendKeys(username);
		objpass.sendKeys(password);
		loginbtn.click();
		Thread.sleep(3000);
	}

}
