//package utilities;
//import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//
//public class ExcelTReader {
//    private Workbook workbook;
//    private Sheet sheet;
//
//    public ExcelTReader(String filePath, String sheetName) throws IOException {
//        FileInputStream fis = new FileInputStream(filePath);
//        workbook = new XSSFWorkbook(fis);
//        sheet = workbook.getSheet(sheetName);
//    }
//
//
//    public String getCellData(int rowNum, int colNum) {
//        Row row = sheet.getRow(rowNum);
//        if (row == null) {
//            return "";
//        }
//
//        Cell cell = row.getCell(colNum);
//        if (cell == null) {
//            return "";
//        }
//
//        if (cell.getCellType() == CellType.STRING) {
//            return cell.getStringCellValue();
//        } else if (cell.getCellType() == CellType.NUMERIC) {
//            return String.valueOf((int) cell.getNumericCellValue());
//        } else if (cell.getCellType() == CellType.BOOLEAN) {
//            return String.valueOf(cell.getBooleanCellValue());
//        } else {
//            return "";
//        }
//    }
//
//
//    public int getRowCount() {
//        return sheet.getPhysicalNumberOfRows();
//    }
//
//
//    public void close() throws IOException {
//        workbook.close();
//    }
//}



package utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelTReader {
    private Workbook workbook;
    private Sheet sheet;

    public ExcelTReader(String filePath, String sheetName) throws IOException {
        try (FileInputStream fis = new FileInputStream(filePath)) {
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new IllegalArgumentException("Sheet with name " + sheetName + " not found.");
            }
        }
    }

    public String getCellData(int rowNum, int colNum) {
        Row row = sheet.getRow(rowNum);
        if (row == null) {
            return "";
        }

        Cell cell = row.getCell(colNum);
        if (cell == null) {
            return "";
        }

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            default:
                return "";
        }
    }

    public int getRowCount() {
        return sheet.getPhysicalNumberOfRows();
    }

    public int getColumnCount() {
        Row row = sheet.getRow(0);
        if (row == null) {
            return 0;
        }
        return row.getLastCellNum();
    }

    public Object[][] getData() {
        int rowCount = getRowCount();
        int colCount = getColumnCount();
        Object[][] data = new Object[rowCount - 1][colCount];

        for (int i = 1; i < rowCount; i++) {
            Row row = sheet.getRow(i);
            if (row == null) continue;

            for (int j = 0; j < colCount; j++) {
                Cell cell = row.getCell(j);
                data[i - 1][j] = getCellData(i, j);
            }
        }

        return data;
    }

    public void close() throws IOException {
        if (workbook != null) {
            workbook.close();
        }
    }
}
