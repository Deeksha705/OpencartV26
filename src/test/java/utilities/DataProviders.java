package utilities;

import java.io.IOException;
import org.testng.annotations.DataProvider;

public class DataProviders {
	
	 // DataProvider 1
    @DataProvider(name="LoginData")
    public String [][] getData() throws IOException
    {
        // Taking xl file from testData folder
        String path = ".\\testData\\OpenCart_LoginData.xlsx"; 
        
        // Creating an object for XLUtility
        ExcelUtility xlutil = new ExcelUtility(path); 
        
        int totalrows = xlutil.getRowCount("Sheet1");
        int totalcols = xlutil.getCellCount("Sheet1", 1);
        
        // Created for two dimensional array which can store data
        String logindata[][] = new String[totalrows][totalcols]; 
        
        // Read the data from xl storing in two dimensional array
        for(int i=1; i<=totalrows; i++) // 1 (Starts from 1 to skip header)
        {
            for(int j=0; j<totalcols; j++) // 0 (Starts from column index 0)
            {
                // Store data in array (i-1 used because array starts from 0, excel from 1)
                logindata[i-1][j] = xlutil.getCellData("Sheet1", i, j); 
            }
        }
        
        return logindata; // Returning two dimension array
    }
}


