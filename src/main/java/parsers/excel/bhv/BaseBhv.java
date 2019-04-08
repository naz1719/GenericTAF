package parsers.excel.bhv;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

public class BaseBhv {


    public void writeCell(Row row, int columnNumber, String cellValue, CellStyle cellStyle) {
        Cell cell1 = row.createCell(columnNumber);
        cell1.setCellValue(cellValue);
        cell1.setCellStyle(cellStyle);
    }

}
