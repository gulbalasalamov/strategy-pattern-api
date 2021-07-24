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

// Get a Category's playlists
public class PlaylistsStrategy implements AdviceStrategy {

    String categoryName;

    public PlaylistsStrategy(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public List<String> execute() {
        CategoryStrategy categoryStrategy = new CategoryStrategy();
        String category_id = categoryStrategy.getByName(categoryName);
        if (category_id.isEmpty()) {
            System.out.println("Unknown category name");
            return null;
        }
        PlaylistsStrategy playlistsStrategy = new PlaylistsStrategy(categoryName);
        return playlistsStrategy.getAllForCategory(category_id);
    }


    public List<String> getAllForCategory(String category_id) {
        List<String> playlists = new ArrayList<>();
        try {
            playlists = getPlaylists(String.format("categories/%s/playlists", category_id));
        } catch (NullPointerException e) {
            //
        }
        return playlists;
    }

    private List<String> getPlaylists(String endpoint) {
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
