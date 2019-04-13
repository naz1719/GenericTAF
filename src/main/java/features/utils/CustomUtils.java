package features.utils;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.jayway.restassured.response.Response;
import org.apache.commons.lang3.text.StrSubstitutor;
import org.testng.reporters.Files;

import javax.xml.bind.JAXB;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;


public class CustomUtils {

    private static final String GENERAL_PROPERTIES_FILE_NAME = "general.properties";
    private static final String CONFIG_PROPERTIES_FILE_NAME = "config.properties";
    private static final String TEST_DATA_PATH = System.getProperty("testDataPath");


    public static String getConfigPropFilePath() throws Exception {
        File prpFile = null;
        prpFile = new File(TEST_DATA_PATH + File.separator + CONFIG_PROPERTIES_FILE_NAME);
        if (!prpFile.exists() || !prpFile.canRead()) {
            throw new Exception("ERROR : The file :" + prpFile.getAbsolutePath() + " is missing");
        }
        return prpFile.getAbsolutePath();
    }


    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        final Set<Object> seen = new HashSet<>();
        return t -> seen.add(keyExtractor.apply(t));
    }


    public static String getSQLScriptByPatternName(String templateName) throws RuntimeException {
        InputStream templatePath = null;
        String fileNume = "db_scripts/" + templateName;
        try {
            templatePath = CustomUtils.class.getClassLoader().getResourceAsStream(fileNume);
            return Files.readFile(templatePath);
        } catch (Exception e) {
            throw new RuntimeException("File [" + fileNume + "] is missing");
        }

    }

    //For Report
    public static String jaxbObjectToXML(Object customer) {
        StringWriter sw = new StringWriter();
        JAXB.marshal(customer, sw);
        return sw.toString();
    }

    public static <T> String jaxbObjectListToXML(List<T> pojoList) {
        StringWriter sw = new StringWriter();
        for (T obj : pojoList) {
            JAXB.marshal(obj, sw);
        }

        return sw.toString();
    }


    public static String prepareStatement(String templateName, Map<String, String> valuesMap) {
        String templateString = CustomUtils.getSQLScriptByPatternName(templateName);
        StrSubstitutor sub = new StrSubstitutor(valuesMap);
        return sub.replace(templateString);
    }

    public static String prepareJson(String templateName, Map<String, String> valuesMap) {
        String templateString = CustomUtils.getJSONFileByPatternName(templateName);
        StrSubstitutor sub = new StrSubstitutor(valuesMap);
        return sub.replace(templateString);
    }


    public static String getJSONFileByPatternName(String templateName) throws RuntimeException {
        InputStream templatePath = null;
        String fileNume = "json_templates/" + templateName;
        try {
            templatePath = CustomUtils.class.getClassLoader().getResourceAsStream(fileNume);
            return Files.readFile(templatePath);
        } catch (Exception e) {
            throw new RuntimeException("File [" + fileNume + "] is missing");
        }

    }

    public static String getPrettyPrintJson(Response response) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(response.asString());
        return gson.toJson(je);
    }

    public static String getAuthorizationHeader(String username, String password) {
        String authString = username + ":" + password;
        byte[] authEncBytes = Base64.getEncoder().encode(authString.getBytes());
        String authStringEnc = new String(authEncBytes);
        return "Basic " + authStringEnc;
    }


    public static String getGeneralPropFilePath() throws Exception {
        File prpFile = null;
        prpFile = new File(getDataStorDir() + File.separator + GENERAL_PROPERTIES_FILE_NAME);
        if (!prpFile.exists() || !prpFile.canRead()) {
            throw new Exception("ERROR : The file :" + prpFile.getAbsolutePath() + " is missing");
        }
        return prpFile.getAbsolutePath();
    }

    public static File getDataStorDir() {
        File dd = new File(System.getProperty("user.dir"));
        if (!dd.exists()) {
            dd.mkdirs();
        }
        return dd;
    }


    public static ByteArrayOutputStream getByteArray(String data) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(byteArrayOutputStream);
        try {
            out.write(data.getBytes());
            byteArrayOutputStream.flush();
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream;

    }

    public static void checkTestData(String excelPath) throws Exception {
        File testDataFile = new File(excelPath);
        if (!testDataFile.exists() || !testDataFile.canRead()) {
            throw new Exception("ERROR : Sorry the file :" + testDataFile.getAbsolutePath() + " is missing, you can download files from web if you define System Variable localTestData=false");
        }
    }

    private static void printResultSet(ResultSet result) throws SQLException {
        ResultSetMetaData rsmd = result.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        while (result.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1) System.out.print(",  ");
                String columnValue = result.getString(i);
                System.out.print(columnValue + " " + rsmd.getColumnName(i));
            }
            System.out.println("");
        }
    }


    public static XMLGregorianCalendar getCurrentData(String pattern) throws DatatypeConfigurationException {
        Date date = new Date();
        Date newDate;
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        newDate = c.getTime();
        return DatatypeFactory.newInstance().newXMLGregorianCalendar(new SimpleDateFormat(pattern).format(newDate));
    }


}

