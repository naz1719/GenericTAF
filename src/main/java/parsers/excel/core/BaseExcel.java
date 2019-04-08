package parsers.excel.core;

import parsers.excel.annotation.ExcelCell;
import parsers.excel.core.exception.ModelInstantiationException;
import parsers.excel.utils.ExcelFileUtils;
import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import static parsers.excel.utils.ExcelFileUtils.getActualRowCount;

public class BaseExcel {
    static Logger LOG = Logger.getLogger(BaseExcel.class.getName());

    protected Workbook workbook;
    protected String fileName;

    public BaseExcel(String fileName) {
        this.fileName = fileName;
    }

    public BaseExcel(File file) {
        this.fileName = file.getPath();
    }


    public BaseExcel openFile() {
        try {
            FileInputStream inputStream = new FileInputStream(fileName);
            workbook = WorkbookFactory.create(inputStream);
            inputStream.close();
        } catch (FileNotFoundException e) {
            LOG.error("File: " + fileName + " was not found");
        } catch (IOException e) {
            LOG.error("Error in IO stream");
        } catch (InvalidFormatException e) {
            LOG.error("Invalid file format");
        }
        if (workbook == null) {
            throw new NullPointerException("Workbook wasn't instantiated properly");
        }
        return this;
    }

    public BaseExcel saveChangesToFile() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            workbook.write(fileOutputStream);
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            LOG.error("File: " + fileName + " was not found");
        } catch (IOException e) {
            LOG.error("Error in IO stream");
        }
        return this;
    }


    public Workbook getWorkbook() {
        return workbook;
    }

    //    for sheet
    public Sheet createSheet(String sheetName) {
        Sheet sheet;
        try {
            sheet = workbook.createSheet(sheetName);
        } catch (IllegalArgumentException exception) {
            workbook.removeSheetAt(workbook.getSheetIndex(sheetName));
            sheet = workbook.createSheet(sheetName);
        }

        return sheet;
    }

    public Sheet getSheet(String sheetName) {
        return workbook.getSheet(sheetName);
    }

    //  for cell style
    public CellStyle createCellStyle() {
        return workbook.createCellStyle();
    }

    public Font createFont() {
        return workbook.createFont();
    }

    public CreationHelper getCreationHelper() {
        return workbook.getCreationHelper();
    }


    //  for formula
    public CellType evaluateAllFormulaCells(Cell cell) {
        return workbook.getCreationHelper().createFormulaEvaluator().evaluateFormulaCellEnum(cell);
    }


    //    Sheet methods
    public boolean isLastRevRow(int rownum, String sheetName) {
        return isRowRevEmpty(rownum, sheetName) && isRowRevEmpty(rownum + 1, sheetName) && isRowRevEmpty(rownum + 2, sheetName);
    }
    public boolean isRowRevEmpty(int rownum, String sheetName) {
        Row row = workbook.getSheet(sheetName).getRow(rownum);
        return ExcelFileUtils.isRowEmpty(row);
    }

    //    Fields method
    public String getField(Cell cellValue) {
        String string;
        try {
            switch (cellValue.getCellTypeEnum()) {
                case BOOLEAN:
                    string = String.valueOf(cellValue.getBooleanCellValue());
                    break;
                case NUMERIC:
//                    if (DateUtil.isValidExcelDate(cellValue.getNumericCellValue())) {
//                        string = String.valueOf(cellValue.getDateCellValue());
//                    }else {
                    string = String.valueOf(cellValue.getNumericCellValue());
//                    }
                    break;
                case STRING:
                    string = cellValue.getStringCellValue();
                    break;
                case BLANK:
                    string = "0.0";
                    break;
                case ERROR:
                    string = String.valueOf(cellValue.getErrorCellValue());
                    break;

                case FORMULA:
                    workbook.getCreationHelper().createFormulaEvaluator().evaluateInCell(cellValue);
                    string = String.valueOf(cellValue.getNumericCellValue());
                    break;
                default:
                    string = "Wow CELL have unrecognized type";
                    break;
            }
        } catch (NullPointerException e) {
            return String.valueOf(0);
        }
        return string;
    }


    private <T> T tailSetFieldValue(Row currentRow, Class<? super T> type, T instance) {
        for (Field field : type.getDeclaredFields()) {

            ExcelCell index = field.getAnnotation(ExcelCell.class);
            if (index != null) {
                Cell cell = currentRow.getCell(index.value());

                try {
                    field.setAccessible(true);
                    field.set(instance, getField(cell));
                } catch (IllegalAccessException e) {
                    LOG.error(field.getName() + e);
                }
            }
        }
        return instance;
    }
    public <T> List<T> unmarshal(Class<T> type, String sheetName, int skip) {
        Sheet sheet = workbook.getSheet(sheetName);

        int rowCount = getActualRowCount(sheet, skip);
        List<T> list = new ArrayList<>(rowCount);

        for (Row currentRow : sheet) {

            if (skip(currentRow, skip))
                continue;

            if (currentRow.getRowNum() <= rowCount) {
                T t = mapRowToModel(currentRow, type);
                list.add(t);
            }
        }
        return list;
    }


    //    Some additional methods
    private <T> T mapRowToModel(Row currentRow, Class<T> type) {
        T instance;
        try {
            instance = type.getDeclaredConstructor().newInstance();
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException e) {
            throw new ModelInstantiationException("Cannot create a new instance of " + type.getName());
        }

        return setFieldValue(currentRow, type, instance);
    }
    private <T> T setFieldValue(Row currentRow, Class<? super T> subclass, T instance) {
        return subclass == null
                ? instance
                : tailSetFieldValue(currentRow, subclass, setFieldValue(currentRow, subclass.getSuperclass(), instance));
    }
    private boolean skip(final Row currentRow, int skip) {
        return currentRow.getRowNum() + 1 <= skip;
    }
}
