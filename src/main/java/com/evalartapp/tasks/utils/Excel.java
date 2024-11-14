package com.evalartapp.tasks.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Excel {
    private static final Logger logger = Logger.getLogger(Excel.class.getName());
    private static final String PATH_FILE = "src/main/resources/data/Data.xlsx";
    private Sheet sheet;

    public Excel(String sheetName) {
        try {
            FileInputStream inputStream = new FileInputStream(PATH_FILE);
            try (Workbook workbook = WorkbookFactory.create(inputStream)) {
                sheet = workbook.getSheet(sheetName);
            }

            if (sheet == null) {
                logger.log(Level.WARNING, "Hoja no encontrada: {0}", sheetName);
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error al leer el archivo de Excel", e);
        }
    }

    public String leerDatos(int rowNum, int colNum) {
        if (sheet == null) {
            throw new IllegalStateException("La hoja no está inicializada");
        }

        Row row = sheet.getRow(rowNum);
        if (row == null) {
            logger.log(Level.WARNING, "Fila no encontrada: {0}", rowNum);
            return "";
        }

        Cell cell = row.getCell(colNum);
        if (cell == null) {
            logger.log(Level.WARNING, "Celda no encontrada: {0},{1}", new Object[]{rowNum, colNum});
            return "";
        }

        if (cell.getCellType() == CellType.BLANK) {
            logger.log(Level.INFO, "La celda está en blanco: {0},{1}", new Object[]{rowNum, colNum});
            return "";
        }

        switch (cell.getCellType()) {
            case STRING:
                String stringValue = cell.getStringCellValue();
                return stringValue.isEmpty() ? "" : stringValue;
            case NUMERIC:
                double numericValue = cell.getNumericCellValue();
                long longValue = Math.round(numericValue);
                if (numericValue == longValue) {
                    return Long.toString(longValue);
                } else {
                    return Double.toString(numericValue);
                }
            case BOOLEAN:
                return Boolean.toString(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                logger.log(Level.WARNING, "Tipo de celda no válido: {0}", cell.getCellType());
                return "";
        }
    }


    public void escribirDato(String sheetName, int rowNum, int colNum, String value) {
        try {
            XSSFWorkbook workbook;
            try (FileInputStream inputStream = new FileInputStream(PATH_FILE)) {
                workbook = new XSSFWorkbook(inputStream);
            }

            XSSFSheet xssfSheet = workbook.getSheet(sheetName);
            if (xssfSheet == null) {
                xssfSheet = workbook.createSheet(sheetName);
            }

            Row row = xssfSheet.getRow(rowNum);
            if (row == null) {
                row = xssfSheet.createRow(rowNum);
            }

            Cell cell = row.getCell(colNum);
            if (cell == null) {
                cell = row.createCell(colNum);
            }

            cell.setCellValue(value);

            try (FileOutputStream outputStream = new FileOutputStream(PATH_FILE)) {
                workbook.write(outputStream);
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error al escribir en el archivo de Excel", e);
        }
    }
}
