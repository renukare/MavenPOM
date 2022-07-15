package commonFunctions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;
public class AddUserPage {
	WebDriver driver;
	public AddUserPage(WebDriver driver)
	{
		this.driver=driver;
	}
	//define repository
	@FindBy(xpath="//b[normalize-space()='Admin']")
	WebElement objadmin;
	@FindBy(xpath="//a[@id='menu_admin_UserManagement']")
	WebElement objusermgt;
	@FindBy(xpath="(//a[normalize-space()='Users'])[1]")
	WebElement objuser;
	@FindBy(xpath="//input[@id='btnAdd']")
	WebElement objadd;
	@FindBy(name="systemUser[userType]")
	WebElement objuserrole;
	@FindBy(name ="systemUser[employeeName][empName]")
	WebElement objempname;
	@FindBy(name="systemUser[userName]")
	WebElement objusername;
	@FindBy(name="systemUser[status]")
	WebElement objstatus;
	@FindBy(name="systemUser[password]")
	WebElement objpassword;
	@FindBy(name="systemUser[confirmPassword]")
	WebElement objcpassword;
	@FindBy(name="btnSave")
	WebElement objsavebtn;
	public boolean verifyadduser(String userrole,String empname,String username,String password,String cpassword) throws Throwable
	{
		Actions ac = new Actions(driver);
		ac.moveToElement(this.objadmin).perform();
		Thread.sleep(2000);
		ac.moveToElement(this.objusermgt).perform();
		Thread.sleep(2000);
		ac.moveToElement(this.objuser).click().perform();
		Thread.sleep(4000);
		this.objadd.click();
		Thread.sleep(4000);
		new Select(this.objuserrole).selectByVisibleText(userrole);	
		Thread.sleep(2000);
		this.objempname.sendKeys(empname);
		Thread.sleep(2000);
		this.objusername.sendKeys(username);
		Thread.sleep(2000);
		this.objpassword.sendKeys(password);
		Thread.sleep(2000);
		this.objcpassword.sendKeys(cpassword);
		this.objsavebtn.click();
		Thread.sleep(3000);
		String expected = "viewSystemUsers";
		String actual = driver.getCurrentUrl();
		if(actual.contains(expected))
		{
			Reporter.log("Add user success",true);
			return true;
		}
		else
		{
			Reporter.log("Add user fail",true);	
			return false;
		}
	}

}
