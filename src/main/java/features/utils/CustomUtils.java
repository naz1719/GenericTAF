package features.utils;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.jayway.restassured.response.Response;

import java.io.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;


public class CustomUtils {

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        final Set<Object> seen = new HashSet<>();
        return t -> seen.add(keyExtractor.apply(t));
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


    public static File getCurrentUserDirectory() {
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
}

