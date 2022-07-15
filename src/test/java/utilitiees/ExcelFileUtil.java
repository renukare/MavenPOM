package utilitiees;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFileUtil {
	XSSFWorkbook wb;
	//create constructor for reading excel path
	public ExcelFileUtil(String excelpath) throws Throwable
	{
		FileInputStream fi = new FileInputStream(excelpath);
	wb = new XSSFWorkbook(fi);	
	}
//count number of rows in a sheet
	public int rowCount(String sheetname)
	{
		return wb.getSheet(sheetname).getLastRowNum();
	}
//count no.of cells in a row
	public int cellCount(String sheetname)
	{
		return wb.getSheet(sheetname).getRow(0).getLastCellNum();
	}
	//read cell data
	public String getCellData(String sheetname, int row,int column)
	{
		String data = "";
		if(wb.getSheet(sheetname).getRow(row).getCell(column).getCellType()==Cell.CELL_TYPE_NUMERIC)
		{
			int celldata=(int)wb.getSheet(sheetname).getRow(row).getCell(column).getNumericCellValue();
			data = String.valueOf(celldata);
		}
		else
		{
			data = wb.getSheet(sheetname).getRow(row).getCell(column).getStringCellValue();
		}
		return data;
	}
	
	public void setCellData(String sheetName,int row,int column,String status,String writeexcel)throws Throwable
	{
		//get sheet from wb
		XSSFSheet ws = wb.getSheet(sheetName);
		//get row from sheet
		XSSFRow rowNum =ws.getRow(row);
		//create cell in a row
		XSSFCell cell =rowNum.createCell(column);
		//write your status
		cell.setCellValue(status);
		if(status.equalsIgnoreCase("Pass"))
		{
			XSSFCellStyle style =wb.createCellStyle();
			XSSFFont font =wb.createFont();
			//.colour text
			font.setColor(IndexedColors.BRIGHT_GREEN.getIndex());
			font.setBold(true);
			font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
			style.setFont(font);
			rowNum.getCell(column).setCellStyle(style);
		}
		else if(status.equalsIgnoreCase("Fail"))
		{
			XSSFCellStyle style =wb.createCellStyle();
			XSSFFont font =wb.createFont();
			//.colour text
			font.setColor(IndexedColors.RED.getIndex());
			font.setBold(true);
			font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
			style.setFont(font);
			rowNum.getCell(column).setCellStyle(style);
		}
		else if(status.equalsIgnoreCase("Blocked"))
		{
			XSSFCellStyle style =wb.createCellStyle();
			XSSFFont font =wb.createFont();
			//.colour text
			font.setColor(IndexedColors.DARK_BLUE.getIndex());
			font.setBold(true);
			font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
			style.setFont(font);
			rowNum.getCell(column).setCellStyle(style);
		}
		FileOutputStream fo = new FileOutputStream(writeexcel);
		wb.write(fo);
		
	}

}
