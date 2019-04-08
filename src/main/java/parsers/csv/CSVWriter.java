package parsers.csv;


import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import parsers.csv.model.Villa;

import java.io.FileWriter;
import java.io.Writer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static constants.CommonConsts.OUTPUT_DIRECTORY;

public class CSVWriter {

    public void writeCsvFromBean(Path path, List list) {
        try {
            Writer writer = new FileWriter(path.toString());

            StatefulBeanToCsv sbc = new StatefulBeanToCsvBuilder(writer)
                    .withSeparator(com.opencsv.CSVWriter.DEFAULT_SEPARATOR)
                    .withLineEnd(com.opencsv.CSVWriter.DEFAULT_LINE_END)
                    .build();

            sbc.write(list);
            writer.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String fileName = "task1.csv";
        List<Villa> list = Arrays.asList(new Villa("Title","price"),new Villa("Title2","price2"));

        CSVWriter csvParser = new CSVWriter();
        csvParser.writeCsvFromBean(Paths.get(OUTPUT_DIRECTORY +"/"+fileName), list);
    }

}
