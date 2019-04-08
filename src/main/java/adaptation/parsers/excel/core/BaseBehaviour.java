package adaptation.parsers.excel.core;


import adaptation.parsers.excel.utils.ExcelFileUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;

import static definition.constants.CommonConsts.FILE_PATH;


public class BaseBehaviour {

    private BaseExcel baseExcel = new BaseExcel(FILE_PATH).openFile();

    private Logger LOG = Logger.getLogger(BaseBehaviour.class);

    public String getField(int rowNumber, Integer cellNumber) {
        Row row = baseExcel.getSheet(FILE_PATH).getRow(rowNumber);
        try {
            return row.getCell(cellNumber).toString();
        } catch (NullPointerException ex) {
            return "";
        }
    }

    public boolean isRowRevEmpty(int rownum) {
        Row row = baseExcel.getSheet(FILE_PATH).getRow(rownum);
        return ExcelFileUtils.isRowEmpty(row);
    }

    public boolean isLastRevRow(int rownum) {
        return isRowRevEmpty(rownum) && isRowRevEmpty(rownum+1) && isRowRevEmpty(rownum+2) &&
                isRowRevEmpty(rownum+3) && isRowRevEmpty(rownum+4) && isRowRevEmpty(rownum+5);
    }
}
