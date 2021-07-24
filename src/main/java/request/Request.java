package request;

import constant.CONFIG;

import java.net.URI;
import java.net.http.HttpRequest;

public class Request {

    public static HttpRequest request(String resource) {
        return HttpRequest.newBuilder()
                .header("Authorization", "Bearer " + CONFIG.ACCESS_TOKEN)
                .header("Content-type", "application/json")
                .uri(URI.create(CONFIG.API_SERVER + "/v1/browse/" + resource))
                .GET()
                .build();
    }

}
