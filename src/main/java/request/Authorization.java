package request;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import constant.CONFIG;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/*
https://developer.spotify.com/documentation/web-api/
 */
public class Authorization {
    //private final int port = 8080;
    private final int port = 8888;

    public boolean authorize() {
        provideLink();
        Server.start(port);
        return requestAccessToken();
    }

    private void provideLink() {
        System.out.println("use this link to request the access code:");
        String url = String.format(CONFIG.AUTHORIZATION_URL +
                        "client_id=%s&" +
                        "redirect_uri=%s&" +
                        "response_type=code",
                CONFIG.CLIENT_ID,
                CONFIG.REDIRECT_URL);
        System.out.println(url);
    }

    private boolean requestAccessToken() {
        System.out.println("code received");
        System.out.println("making http request for access_tocken...");

        HttpRequest request =
                HttpRequest
                        .newBuilder()
                        .header("Content-Type", "application/x-www-form-urlencoded")
                        .uri(URI.create(CONFIG.AUTH_SERVER + "/api/token"))
                        .POST(HttpRequest.BodyPublishers.ofString(
                                "grant_type=authorization_code" +
                                        "&code=" + Server.getCode() +
                                        "&client_id=" + CONFIG.CLIENT_ID +
                                        "&client_secret=" + CONFIG.CLIENT_SECRET +
                                        "&redirect_uri=" + CONFIG.REDIRECT_URL
                        )).build();

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response != null && response.body().contains("access_token")) {
                parseAccessToken(response.body());
                System.out.println("response: " + response.body());
                System.out.println("Success!");
            }
            return response.body().contains("access_token");
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
        //return response.body().contains("access_token");


        return false;
    }


    void parseAccessToken(String body) {
        JsonObject jo = JsonParser.parseString(body).getAsJsonObject();
        CONFIG.ACCESS_TOKEN = jo.get("access_token").getAsString();
    }

}