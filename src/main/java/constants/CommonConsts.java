package constants;

import java.util.Arrays;
import java.util.List;

public final class CommonConsts {
    public static final String PATH_TO_CONFIGURATION_PROPERTIES = "./configuration.properties";
    public static final String ESCAPE_PROPERTY = "org.uncommons.reportng.escape-output";

    //    URL constants
    public static final String WEB_SITE_URL = "https://www.google.com.ua/";


    public static String ZERO = "0";


    //  File & Path & Sheet constants
    public static String AMAZON_CO_UK_PATH_FILE_FLIE = "..\\output\\AmazonCoUK.xlsx";
    public static String AMAZON_IN_PATH_FILE = "..\\output\\amazon.in";
    public static final String OUTPUT_DIRECTORY = "output";
    public static final String FOLDER_PATH = "..\\input";

    public static final String SHEET1 = "Sheet1";
    public static String FILE_PATH = "file.xlsx";



    //    Headers
    public static List<String> SAMPLE_HEADERS = Arrays.asList("Header", "Header", "Header", "Header");

    public CommonConsts() {
    }
}
