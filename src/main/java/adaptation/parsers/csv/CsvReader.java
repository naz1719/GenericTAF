package adaptation.parsers.csv;


import com.opencsv.CSVReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {
    private static CsvReader instance;

    private CsvReader() {
    }

    public static CsvReader getInstance() {
        if (instance == null) {
            instance = new CsvReader();
        }
        return instance;
    }

    public List<String> parseUsers(String csvPath, Character separator) throws IOException {
        CSVReader reader = null;
        try {
            reader = new CSVReader(new FileReader(csvPath), separator);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        List<String> list = new ArrayList<>();
        String[] record = null;
        while ((record = reader.readNext()) != null) {
            list.add(record[0]);
        }
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
