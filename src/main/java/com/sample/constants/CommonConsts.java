package com.sample.constants;

import java.util.Arrays;
import java.util.List;

public final class CommonConsts {
    public static final String PATH_TO_CONFIGURATION_PROPERTIES = "./configuration.properties";
    public static final String ESCAPE_PROPERTY = "org.uncommons.reportng.escape-output";

    //    URL constants
    public static final String WEB_SITE_URL = "https://www.google.com.ua/";

    //  File & Path & Sheet constants
    public static final String FOLDER_PATH = ".";
    public static String FILE_PATH = "file.xlsx";
    public static String SAMPLE_SHEET = "sheetName";


    //    Headers
    public static List<String> SAMPLE_HEADERS = Arrays.asList("Header", "Header", "Header", "Header");

    public CommonConsts() {
    }
}
