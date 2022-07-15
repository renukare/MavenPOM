package commonFunctions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;
public class AddEmpPage {
	WebDriver driver;
	public AddEmpPage(WebDriver driver)
	{
		this.driver=driver;
	}
	//define repository for add emp page
	@FindBy(xpath="//b[normalize-space()='PIM']")
	WebElement objpim;
	@FindBy(linkText="Add Employee")
	WebElement objaddemp;
	@FindBy(name="firstName")
	WebElement objfirstname;
	@FindBy(name="middleName")
	WebElement objmiddlename;
	@FindBy(name="lastName")
	WebElement objlstname;
	@FindBy(xpath="//input[@id='btnSave']")
	WebElement objsavebtn;
	@FindBy(xpath="//input[@id='employeeId']")
	WebElement objexpectedempid;
	@FindBy(xpath="//input[@id='personal_txtEmployeeId']")
	WebElement objactualempid;
	public boolean verifyemployee(String fname,String mname,String lname) throws Throwable
	{
		Actions ac = new Actions(driver);
		ac.moveToElement(this.objpim).click().perform();
		Thread.sleep(2000);
		ac.moveToElement(this.objaddemp).click().perform();
		Thread.sleep(2000);
		this.objfirstname.sendKeys(fname);
		this.objmiddlename.sendKeys(mname);
		this.objlstname.sendKeys(lname);
		Thread.sleep(4000);
		String expectedid=this.objexpectedempid.getAttribute("value");
		Thread.sleep(2000);
		this.objsavebtn.click();
		Thread.sleep(4000);
		String actualid=this.objactualempid.getAttribute("value");
		if(expectedid.equalsIgnoreCase(actualid))
		{
			Reporter.log("Add emp success"+expectedid+"     "+actualid,true);
			return true;
		}
		else
		{
			Reporter.log("Add emp fail"+expectedid+"      "+actualid);
			return false;
		}
	}

}