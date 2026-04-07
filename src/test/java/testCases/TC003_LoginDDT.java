package testCases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass{
	
	@Test(dataProvider ="LoginData", dataProviderClass = DataProviders.class)
    public void verify_loginDDT(String email, String pwd, String exp) {
        // Line 16: Corrected the typo "Starting"
        logger.info("******* Starting TC_003_LoginTestDDT *******");

  try {    
            // HomePage actions
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            hp.clickLogin();

            // LoginPage actions - pulling data from your properties file 'p'
            LoginPage lp = new LoginPage(driver);
            lp.setEmail(email);
            lp.setPassword(pwd);
            lp.clickLogin();
            
            //Myaccount
            MyAccountPage macc = new MyAccountPage(driver);
            boolean targetPage =macc.isMyAccountPageExists();
            
            if (exp.equalsIgnoreCase("Valid")) {
            	if(targetPage==true)
            	{
            		macc.clickLogout();
            		Assert.assertTrue(true);
            		
            	}
            	else {
            		
            		Assert.assertFalse(false);
            	}
            	
            }
            
            if (exp.equalsIgnoreCase("Invalid")) {
            	if(targetPage==true)
            	{
            		macc.clickLogout();
            		Assert.assertTrue(true);
            		
            	}
                else {
            		
            		Assert.assertFalse(true);
            	}
                        
            }
  }
  catch(Exception e){
	  Assert.fail();	  
  }
  
            logger.info("******* Finished TC_003_LoginTestDDT *******");

	}
}


