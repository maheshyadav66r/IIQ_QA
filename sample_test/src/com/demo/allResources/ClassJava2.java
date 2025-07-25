package com.demo.allResources;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

public class ClassJava2 {
	    public static void main(String[] args) {
	        String excelFilePath = "C:\\path\\to\\file1.xlsm"; // Change to your file path
	        String textFilePath = "C:\\path\\to\\output.txt";  // Output text file

	        try (FileInputStream fis = new FileInputStream(excelFilePath);
	             Workbook workbook = new XSSFWorkbook(fis);      // `.xlsm` is read using XSSFWorkbook
	             BufferedWriter bw = new BufferedWriter(new FileWriter(textFilePath))) {

	            Sheet sheet = workbook.getSheetAt(0); // Read the first sheet
	            for (Row row : sheet) {
	                StringBuilder rowData = new StringBuilder();
	                for (Cell cell : row) {
	                    rowData.append(getCellValue(cell)).append("\t");  // Tab-separated columns
	                }
	                bw.write(rowData.toString().trim());      // Trim trailing tab
	                bw.newLine();                             // Move to the next row
	            }

	            System.out.println("Data successfully written to " + textFilePath);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    private static String getCellValue(Cell cell) {
	        if (cell == null) return "";
	        switch (cell.getCellType()) {
	            case STRING:
	                return cell.getStringCellValue();
	            case NUMERIC:
	                if (DateUtil.isCellDateFormatted(cell)) {
	                    return cell.getDateCellValue().toString(); // Format date if needed
	                } else {
	                    return String.valueOf(cell.getNumericCellValue());
	                }
	            case BOOLEAN:
	                return String.valueOf(cell.getBooleanCellValue());
	            case FORMULA:
	                return cell.getCellFormula();
	            default:
	                return "";
	        }
	    }
	}


}
