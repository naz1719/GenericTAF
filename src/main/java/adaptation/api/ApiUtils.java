package adaptation.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.jayway.restassured.response.Response;

import java.util.Base64;

public class ApiUtils {


    public static String getAuthorizationHeader(String username, String password) {
        String authString = username + ":" + password;
        byte[] authEncBytes = Base64.getEncoder().encode(authString.getBytes());
        String authStringEnc = new String(authEncBytes);
        return "Basic " + authStringEnc;
    }

}
