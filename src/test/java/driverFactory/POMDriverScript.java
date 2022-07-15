package driverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import commonFunctions.AddEmpPage;
import commonFunctions.AddUserPage;
import commonFunctions.LogOutPage;
import commonFunctions.LoginPage;
import utilitiees.ExcelFileUtil;

public class POMDriverScript {
	String inputpath="C:\\Users\\Renuka c\\eclipse-workspace\\MavenPOM\\TestInput\\UserCreation.xlsx";
	String outputpath = "C:\\Users\\Renuka c\\eclipse-workspace\\MavenPOM\\TestOutput\\POMresults.xlsx";
	WebDriver driver;
	@BeforeTest
	public void adminlogin() throws Throwable
	{
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://orangehrm.qedgetech.com");
		LoginPage login = PageFactory.initElements(driver, LoginPage.class);
		login.verifyLogin("Admin", "Qedge123!@#");
	}
	@Test
	public void validateuser() throws Throwable
	{
		ExcelFileUtil xl = new ExcelFileUtil(inputpath);
		int rc = xl.rowCount("UserData");
		int cc = xl.cellCount("UserData");
		Reporter.log("no.of rows are:"+rc+"       "+"no.of cells in a row are::"+cc);
		for(int i=1;i<=rc;i++)
		{
			String role = xl.getCellData("UserData", i, 0);
			String employee = xl.getCellData("UserData", i, 1);
			String username=xl.getCellData("UserData", i, 2);
			String password = xl.getCellData("UserData", i, 3);
			String cpassword = xl.getCellData("UserData", i, 4);
			AddUserPage user = PageFactory.initElements(driver, AddUserPage.class);
			boolean res = user.verifyadduser(role, employee, username, password, cpassword);
			if(res)
			{
				Reporter.log("Add user Success",true);
				xl.setCellData("UserData", i, 5, "Pass", outputpath);
			}
			else
			{
				Reporter.log("Add user fail",true);
				xl.setCellData("UserData", i, 6, "Fail", outputpath);
			}
		}
	
	
	
	}
	@Test
	public void validateemployee() throws Throwable
	{
		ExcelFileUtil xl = new ExcelFileUtil(inputpath);
		int rc = xl.rowCount("EmpData");
		int cc = xl.cellCount("EmpData");
		Reporter.log("no.of rows are:"+rc+"     "+"no of cells in a row are:"+cc);
		for(int i =1;i<=rc;i++)
		{
			String fname = xl.getCellData("EmpData", i, 0);
			String mname = xl.getCellData("EmpData", i, 1);
			String lname=xl.getCellData("EmpData", i, 2);
			AddEmpPage emp = PageFactory.initElements(driver, AddEmpPage.class);
			boolean res=emp.verifyemployee(fname, mname, lname);
			if(res)
			{
				xl.setCellData("EmpData", i, 3, "pass", outputpath);
			}
			else
			{
				xl.setCellData("EmpData", i, 4, "Fail", outputpath);
			}
		}
	}
	@AfterTest
	public void adminlogout() throws Throwable
	{
		LogOutPage logout = PageFactory.initElements(driver, LogOutPage.class);
		logout.verifyLogout();
		driver.close();
	}
}



















