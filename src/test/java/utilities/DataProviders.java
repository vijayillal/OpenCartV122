package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	//Data Provider1
	@DataProvider(name="Logindata")
	public String[][] getData() throws IOException
	{
		String path=".\\testData/OpenCartLoginData.xlsx";  //taking xl file from test data
		ExcelUtility xllutil= new ExcelUtility(path);  // creating the object for excel utilities
		
		int totalrow=xllutil.getRowCount("Sheet1");
		int totalcols=xllutil.getCellCount("Sheet1", 1);
		
		String logindata[][]= new String[totalrow][totalcols];  // created 2 dimensional array to store data
		
		for(int i=1; i<=totalrow; i++)  // reading 2 dimensional array value of i=row 
		{
			for(int j=0; j<totalcols; j++)  // reading 2 dimensional array value of j=column 
			{
				logindata[i-1][j]= xllutil.getCellData("Sheet1", i, j);  //1,0  i=row j=column
			}
		}
		return logindata;  // returning 2 dimensional array
		
	}
}
