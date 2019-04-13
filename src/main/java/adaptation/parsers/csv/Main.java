package adaptation.parsers.csv;

import adaptation.parsers.csv.model.Villa;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static definition.constants.CommonConsts.OUTPUT_DIRECTORY;

public class Main {
    public static void main(String[] args) throws IOException {

        //------ Writing excel ---------
        String fileName = "task1.csv";
        List<Villa> list = Arrays.asList(new Villa("Title", "price"), new Villa("Title2", "price2"));

        String filePath = OUTPUT_DIRECTORY + "/" + fileName;
        CsvWriter.getInstance().writeCsvFromBean(filePath, list);


        //------ Reading excel ---------
        Character COMA_SEPERATOR = ',';
        String USERS_CSV_PATH = filePath;

        List<String> parseUsers = CsvReader.getInstance().parseUsers(USERS_CSV_PATH, COMA_SEPERATOR);
        System.out.println(parseUsers);

    }
}
