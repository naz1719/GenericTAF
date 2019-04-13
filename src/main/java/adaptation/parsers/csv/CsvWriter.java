package adaptation.parsers.csv;


import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

import java.io.FileWriter;
import java.io.Writer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class CsvWriter {

    private static CsvWriter instance;

    private CsvWriter() {
    }

    public static CsvWriter getInstance() {
        if (instance == null) {
            instance = new CsvWriter();
        }
        return instance;
    }

    public void writeCsvFromBean(String filePath, List<?> list) {
        Path path = Paths.get(filePath);
        try {
            Writer writer = new FileWriter(path.toString());

            StatefulBeanToCsv sbc = new StatefulBeanToCsvBuilder(writer)
                    .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                    .withLineEnd(CSVWriter.DEFAULT_LINE_END)
                    .build();

            sbc.write(list);
            writer.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
