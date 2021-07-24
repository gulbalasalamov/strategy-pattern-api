package strategy;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import request.Api;
import service.AdviceStrategy;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Get All New Releases
GET https://api.spotify.com/v1/browse/new-releases
https://developer.spotify.com/documentation/web-api/reference/#category-browse
 */

public class NewReleasesStrategy implements AdviceStrategy {

    @Override
    public List<String> execute() {
        return getAll();
    }

    public static List<String> getAll() {
        List<String> newReleases = new ArrayList<>();
        HttpResponse<String> response = Api.get("new-releases");
        JsonObject body = JsonParser.parseString(response.body()).getAsJsonObject();
        JsonObject categories = body.getAsJsonObject("albums");
        JsonArray items = categories.getAsJsonArray("items");

        for (int i = 0; i < items.size(); i++) {
            JsonObject jsonObject = (JsonObject) items.get(i);
            String name = jsonObject.get("name").getAsString();
            String allArtists = "";
            String href = items.get(i).getAsJsonObject()
                    .get("external_urls").getAsJsonObject()
                    .get("spotify").getAsString();
            JsonArray artists = jsonObject.getAsJsonObject().get("artists").getAsJsonArray();
            String[] artistNames = new String[artists.size()];
            for (int j = 0; j < artists.size(); j++) {
                JsonObject jo = artists.get(j).getAsJsonObject();
                artistNames[j] = jo.get("name").getAsString();
                allArtists = Arrays.toString(artistNames);
            }
            newReleases.add(String.format("%s\n%s\n%s\n", name, allArtists, href));
        }
//        for (JsonElement item : items) {
//            String name = item.getAsJsonObject().get("name").getAsString();
//            //String artists = Artist.getAsArray(item);
//            JsonArray artists = (JsonArray) item.get(i);
//            String href = item.getAsJsonObject()
//                    .get("external_urls").getAsJsonObject()
//                    .get("spotify").getAsString();
//            newReleases.add(String.format("%s\n%s\n%s\n", name, artists, href));
//        }
        return newReleases;
    }

}
