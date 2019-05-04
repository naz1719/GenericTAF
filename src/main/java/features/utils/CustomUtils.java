package features.utils;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.jayway.restassured.response.Response;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
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

    public static File getCurrentUserDirectory() {
        File dd = new File(System.getProperty("user.dir"));
        if (!dd.exists()) {
            dd.mkdirs();
        }
        return dd;
    }

    public static String getGeneralPropFilePath(String filePath) throws Exception {
        File prpFile = null;
        prpFile = new File(getCurrentUserDirectory() + File.separator + filePath);
        if (!prpFile.exists() || !prpFile.canRead()) {
            throw new Exception("ERROR : The file :" + prpFile.getAbsolutePath() + " is missing");
        }
        return prpFile.getAbsolutePath();
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

    public static URL convertStringToURL(String link) {
        URL url;
        try {
            url = new URL(link);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return url;
    }

}

