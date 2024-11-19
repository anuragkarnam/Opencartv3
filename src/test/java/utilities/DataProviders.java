package utilities;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name="LoginData")
	public String[][] dataProvider_1() throws FileNotFoundException, IOException {
	
		String path = "./testData//myxldemo.xlsx";
		DDTUtility ddt = new DDTUtility(path);
		
		int total_rows=ddt.getRowCount("data");
		int total_cols=ddt.getRowCellCount("data", 1);

		String [][] logindata = new String [total_rows][total_cols];

		for(int r = 1; r<=total_rows; r++) {

			for(int c= 0; c<total_cols; c++) {
				logindata[r-1][c] = ddt.getCelldata("data", r, c);
			}
		}
		return logindata;
	}
}
