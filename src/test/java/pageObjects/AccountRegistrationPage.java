package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage{

	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy (xpath="//input[@id='input-firstname']")
	WebElement txtFirstName;
	
	@FindBy (xpath="//input[@id='input-lastname']")
	WebElement txtLastName;
	
	@FindBy (xpath="//input[@id='input-email']")
	WebElement txtEmail;
	
	@FindBy (xpath="//input[@id='input-telephone']")
	WebElement telephone;
	
	@FindBy (xpath="//input[@id='input-password']")
	WebElement pwd;
	
	@FindBy (xpath="//input[@id='input-confirm']")
	WebElement cnfpwd;
	
	@FindBy (xpath="//input[@name='agree']")
	WebElement pvcy;
	
	@FindBy (xpath="//input[@value='Continue']")
	WebElement btnSubmit;
	
	@FindBy (xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement cnfmMessage;
	
	@FindBy (xpath="//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Logout']")
	WebElement btnlogout;
	
	
	public void setFirstName(String fname) {
		txtFirstName.sendKeys(fname);
	}
	
	public void setLastName(String lname) {
		txtLastName.sendKeys(lname);
	}
	
	public void setEmail(String email) {
		txtEmail.sendKeys(email);
	}
	
	public void setphone(String email) {
		telephone.sendKeys(email);
	}
	
	public void pwd(String pass) {
		pwd.sendKeys(pass);
	}
	
	public void confirmpwd(String pass) {
		cnfpwd.sendKeys(pass);
	}
	
	public void privacy() {
		pvcy.click();
	}
	
	public void submit() {
		btnSubmit.click();
	}
	
	public String Message() {
		try {
			return (cnfmMessage.getText());
		} catch (Exception e){
			return (e.getMessage());
		}
	}
	
	public void myLogout() {
		btnlogout.click();
	}
	
}


