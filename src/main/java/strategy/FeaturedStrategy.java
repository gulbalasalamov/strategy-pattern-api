package strategy;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import request.Api;
import service.AdviceStrategy;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

//a list of Spotify-featured playlists with their links fetched from API;
public class FeaturedStrategy implements AdviceStrategy {

    @Override
    public List<String> execute() {
        return getPlaylists("featured-playlists");
    }

    public List<String> getPlaylists(String endpoint) {
        List<String> list = new ArrayList<>();
        HttpResponse<String> response = Api.get(endpoint);
        System.out.println(response.body());
        JsonObject body = JsonParser.parseString(response.body()).getAsJsonObject();
        if (response.statusCode() == 200) {
            JsonObject categories = body.getAsJsonObject("playlists");
            JsonArray items = categories.getAsJsonArray("items");
            for (JsonElement item : items) {
                String name = item.getAsJsonObject().get("name").getAsString();
                String href = item.getAsJsonObject()
                        .get("external_urls").getAsJsonObject()
                        .get("spotify").getAsString();
                list.add(String.format("%s\n%s\n", name, href));
            }
            return list;
        } else {
            System.out.println(response.body());
            return null;
        }
    }


}
