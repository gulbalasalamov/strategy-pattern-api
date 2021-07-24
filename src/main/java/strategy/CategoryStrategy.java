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

/*

Get all Categories
GET https://api.spotify.com/v1/browse/categories

Reference Index
https://developer.spotify.com/documentation/web-api/reference/#category-browse

Objects Index
https://developer.spotify.com/documentation/web-api/reference/#objects-index

 */
public class CategoryStrategy implements AdviceStrategy {
    @Override
    public List<String> execute() {
        return getNames();
    }

    public static void getById(String id) {
        HttpResponse<String> response = Api.get("categories/" + id);
        System.out.println(response.body());
    }

    public String getByName(String name) {
        JsonArray items = getAll();
        String found = "";
        for (JsonElement item : items) {
            String c_name = item.getAsJsonObject().get("name").getAsString();
            if (name.equals(c_name)) {
                found = item.getAsJsonObject().get("id").getAsString();
            }
        }
        return found;
    }

    public List<String> getNames() {
        List<String> names = new ArrayList<>();
        JsonArray items = getAll();
        for (JsonElement item : items) {
            String category = item.getAsJsonObject().get("name").getAsString();
            names.add(category);
        }
        return names;
    }

    public JsonArray getAll() {
        HttpResponse<String> response = Api.get("categories");
        JsonObject body = JsonParser.parseString(response.body()).getAsJsonObject();
        JsonObject categories = body.getAsJsonObject("categories");
        return categories.getAsJsonArray("items");
    }


}
