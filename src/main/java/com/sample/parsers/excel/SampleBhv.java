package com.sample.parsers.excel;

import com.sample.parsers.excel.base.BaseExcel;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;

import java.util.List;

import static com.sample.project.constants.CommonConsts.FILE_PATH;
import static com.sample.project.constants.CommonConsts.SAMPLE_HEADERS;
import static com.sample.project.constants.CommonConsts.SAMPLE_SHEET;

public class SampleBhv {
    private BaseExcel baseExcel = new BaseExcel(FILE_PATH).openFile();
    private Sheet sheet = baseExcel.getSheet(SAMPLE_SHEET);

    private int BEGIN_ROW_CREATED_SHEET = 1;
    private CreationHelper createHelper = baseExcel.getCreationalHelper();


    public void writeHeaders() {

        Row row = sheet.createRow(0);
        row.setHeightInPoints(60);

        for (int i = 0; i < SAMPLE_HEADERS.size(); i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(SAMPLE_HEADERS.get(i));
            cell.setCellStyle(getCellSHeaderStyle());
        }
        baseExcel.saveChangesToFile();
    }

//
//    public void writeBodyOfCell(List<Blog> list) {
//
//        for (int i = 0; i < list.size(); i++) {
//            Row row1 = createCustomRow();
//
//            Cell cell1 = row1.createCell(0);
//            cell1.setCellValue(list.get(i).getTitle());
//            cell1.setCellStyle(getStandardCellStyle());
//
//            Cell cell2 = row1.createCell(1);
//            cell2.setCellValue(list.get(i).getURL());
//            cell2.setCellStyle(getStandardCellStyle());
//        }
//
//        baseExcel.saveChangesToFile();
//    }


    public CellStyle getStandardCellStyle() {

        CellStyle style = baseExcel.createCellStyle();

        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setAlignment(HorizontalAlignment.LEFT);
        return style;
    }
    public CellStyle getHyperLinkCellStyle() {

        CellStyle style = baseExcel.createCellStyle();

        Font font = baseExcel.createFont();
        font.setColor(HSSFColor.LIGHT_BLUE.index);
        style.setFont(font);

        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setAlignment(HorizontalAlignment.LEFT);
        return style;
    }


    private CellStyle getCellSHeaderStyle() {
        CellStyle style = baseExcel.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setFillForegroundColor(IndexedColors.AQUA.getIndex());
        style.setWrapText(true);

        style.setBorderBottom(BorderStyle.MEDIUM);
        style.setBorderLeft(BorderStyle.MEDIUM);
        style.setBorderRight(BorderStyle.MEDIUM);
        style.setBorderTop(BorderStyle.MEDIUM);
        return style;
    }

    public Row createCustomRow() {
        return sheet.createRow(BEGIN_ROW_CREATED_SHEET++);
    }


}
