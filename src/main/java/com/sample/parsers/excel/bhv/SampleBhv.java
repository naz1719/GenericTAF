package com.sample.parsers.excel.bhv;

import com.sample.parsers.excel.core.BaseExcel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import static com.sample.parsers.excel.utils.CellStyleUtils.getCellSHeaderStyle;
import static com.sample.constants.CommonConsts.*;

public class SampleBhv extends BaseBhv {
    private BaseExcel baseExcel = new BaseExcel(FILE_PATH).openFile();
    private Sheet sheet = baseExcel.getSheet(SAMPLE_SHEET);

    private CellStyle headerStyle = getCellSHeaderStyle(baseExcel);

    private int BEGIN_ROW_CREATED_SHEET = 1;


    public void writeHeaders() {

        Row row = sheet.createRow(0);
        row.setHeightInPoints(60);

        for (int i = 0; i < SAMPLE_HEADERS.size(); i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(SAMPLE_HEADERS.get(i));
            cell.setCellStyle(headerStyle);
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




    public Row createCustomRow() {
        return sheet.createRow(BEGIN_ROW_CREATED_SHEET++);
    }
    public Row getRow() {
        return sheet.createRow(BEGIN_ROW_CREATED_SHEET++);
    }

}
