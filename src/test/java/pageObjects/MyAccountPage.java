package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {
	
	 // Constructor
    public MyAccountPage(WebDriver driver) {
        super(driver);
    }

    // Locate the heading element using XPath
    @FindBy(xpath = "//h2")
    WebElement msgHeading;
    
    @FindBy(xpath = "//aside[@id='column-right']//a")
    WebElement linkLogout;
    
   

    // Method to check if the 'My Account' heading is displayed
    public boolean isMyAccountPageExists() {
        try {
            return msgHeading.isDisplayed();
        } catch (Exception e) {
            // Returns false if the element is not found or not visible
            return false;
        }
}
    
    public void clickLogout() {
        linkLogout.click();
    }
}
