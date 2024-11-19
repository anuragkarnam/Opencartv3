package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DDTUtility {

	public static FileInputStream fi; // reading
	public static	FileOutputStream fo; //writing
	public static XSSFWorkbook wo;
	public static XSSFSheet sheet;
	public static XSSFRow row;
	public static XSSFCell cell;
	public static CellStyle style;
	public String path;

	public DDTUtility(String path) {
		this.path = path;
	}

	public void createXLFile(String sheetName, int totalRows, int totalCells, Scanner sc) throws Exception {

		fo = new FileOutputStream(path);
		wo = new XSSFWorkbook();
		sheet = wo.createSheet(sheetName);

		for(int r = 0; r<=totalRows; r++) {
			row = sheet.createRow(r);
			for(int c = 0; c<totalCells; c++) {
				cell = row.createCell(c);
				System.out.print("Please enter the "+r+"th row "+c+"th cell value : ");
				cell.setCellValue(sc.next());
			}
		}
		wo.write(fo);
		wo.close();
		fo.close();
		sc.close();
		System.out.println("file created.... at "+path);

	}

	public void displayCompleteData ( String sheetName) throws FileNotFoundException,IOException {
		fi = new FileInputStream(path); 
		wo = new XSSFWorkbook(fi);
		sheet=wo.getSheet(sheetName);
		int lastRowNum=sheet.getLastRowNum();
		int lastCellNum = sheet.getLastRowNum();

		for(int r=0; r<=lastRowNum; r++) {
			row=sheet.getRow(r);
			for(int c = 0; c<lastCellNum; c++) {
				cell=row.getCell(c);
				System.out.print(cell.toString()+"\t");
			}System.out.println();
		}
		wo.close();
		fi.close();

	}

	public int getRowCount(String sheetName) throws FileNotFoundException,IOException {
		fi = new FileInputStream(path);
		wo = new XSSFWorkbook(fi);
		sheet=wo.getSheet(sheetName);
		int rowCount=sheet.getLastRowNum();
		wo.close();
		fi.close();
		return rowCount;
	}

	public int getRowCellCount(String sheetName, int rowNum) throws FileNotFoundException,IOException {
		fi = new FileInputStream(path);
		wo = new XSSFWorkbook(fi);
		sheet=wo.getSheet(sheetName);
		row=sheet.getRow(rowNum);
		int cellCount = row.getLastCellNum();
		wo.close();
		fi.close();
		return cellCount;
	}

	public String getCelldata(String sheetName, int rowNum, int cellNum) throws IOException,FileNotFoundException {
		fi = new FileInputStream(path);
		wo = new XSSFWorkbook(fi);
		sheet=wo.getSheet(sheetName);
		row=sheet.getRow(rowNum);
		String cellContent;
		cell=row.getCell(cellNum);
		try {
			cellContent=cell.toString();
		}	
		catch(Exception e) {
			cellContent="";	
		}
		wo.close();
		fi.close();
		return cellContent;
	}

	/*
	public void setCellData(String sheetName, int rowNum, int cellNum, String cellData) throws FileNotFoundException,IOException {
		fi = new FileInputStream(path);
		wo = new XSSFWorkbook(fi);
		sheet=wo.getSheet(sheetName);
		row=sheet.getRow(rowNum);
		cell=row.getCell(cellNum);
		cell.setCellValue(cellData);
		fo = new FileOutputStream(path);
		wo.write(fo);
		wo.close();
		fi.close();
		fo.close();
	}
	 */

	// Method to set cell data and create a cell if it doesn't exist
	public void setCellData(String sheetName, int rowNum, int cellNum, String cellData) throws FileNotFoundException, IOException {
		fi = new FileInputStream(path);
		wo = new XSSFWorkbook(fi);
		sheet = wo.getSheet(sheetName);

		// Ensure the row exists
		row = sheet.getRow(rowNum);
		if (row == null) {
			row = sheet.createRow(rowNum);
		}

		// Ensure the cell exists
		cell = row.getCell(cellNum);
		if (cell == null) {
			cell = row.createCell(cellNum);
		}

		// Set the cell value
		cell.setCellValue(cellData);

		fo = new FileOutputStream(path);
		wo.write(fo);
		wo.close();
		fi.close();
		fo.close();
	}

	public void fillGreenBackground(String sheetName, int rowNum, int cellNum) throws IOException,FileNotFoundException{
		fi = new FileInputStream(path);
		wo = new XSSFWorkbook(fi);
		sheet=wo.getSheet(sheetName);
		row=sheet.getRow(rowNum);
		cell=row.getCell(cellNum);
		style=wo.createCellStyle();

		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		cell.setCellStyle(style);
		fo = new FileOutputStream(path);
		wo.write(fo);
		wo.close();
		fi.close();
		fo.close();
	}

	public void fillRedBackground(String sheetName, int rowNum, int cellNum) throws IOException,FileNotFoundException{
		fi = new FileInputStream(path);
		wo = new XSSFWorkbook(fi);
		sheet=wo.getSheet(sheetName);
		row=sheet.getRow(rowNum);
		cell=row.getCell(cellNum);
		style=wo.createCellStyle();

		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		cell.setCellStyle(style);
		fo = new FileOutputStream(path);
		wo.write(fo);
		wo.close();
		fi.close();
		fo.close();
	}

}
/*
	  public void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		DDTUtility.createXLFile("mysheet", 3, 3, sc);
		System.out.println(DDTUtility.getRowCount("mysheet"));
		System.out.println(DDTUtility.getRowCellCount("mysheet",0));
		System.out.println(DDTUtility.getCelldata("mysheet", 2, 1));
		DDTUtility.fillRedBackground("mysheet", 0, 0);
		DDTUtility.fillRedBackground("mysheet", 0, 1);
		DDTUtility.fillRedBackground("mysheet", 0, 2);
		DDTUtility.setCellData("mysheet", 0, 3, "hellya!");
		DDTUtility.displayCompleteData("mysheet");

		sc.close();
	}

}
 */