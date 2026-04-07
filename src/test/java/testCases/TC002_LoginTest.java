package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {
	
	@Test(groups = {"Sanity", "Master"})
    public void verify_login() {
        // Line 16: Corrected the typo "Starting"
        logger.info("******* Starting TC_002_LoginTest *******");

        try {
            // HomePage actions
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            hp.clickLogin();

            // LoginPage actions - pulling data from your properties file 'p'
            LoginPage lp = new LoginPage(driver);
            lp.setEmail(p.getProperty("email"));
            lp.setPassword(p.getProperty("password"));
            lp.clickLogin();

            // MyAccountPage verification
            MyAccountPage macc = new MyAccountPage(driver);
            boolean targetPage = macc.isMyAccountPageExists();

            // More concise assertion
            Assert.assertTrue(targetPage, "Login failed: My Account page not displayed.");
            
        } catch (Exception e) {
            logger.error("Test failed due to an exception: " + e.getMessage());
            Assert.fail("Test failed due to exception.");
        }

        logger.info("******* Finished TC_002_LoginTest *******");
    }
	
	

}
