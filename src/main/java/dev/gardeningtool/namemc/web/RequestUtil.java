package dev.gardeningtool.namemc.web;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.gardeningtool.namemc.friend.Friend;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class RequestUtil {

    @SneakyThrows
    public static String username(UUID uuid) throws IllegalArgumentException {
        Request request = getRequest("https://sessionserver.mojang.com/session/minecraft/profile/" + uuid.toString());
        Map<?, ?> map = request.map();
        if (map == null) {
            throw new IllegalArgumentException(String.format("%s is not a valid UUID!", uuid.toString()));
        }
        return map.get("name").toString();
    }

    @SneakyThrows
    public static UUID uuid(String username) throws IllegalArgumentException {
        Request request = getRequest("https://api.mojang.com/users/profiles/minecraft/" + username + "?at=0");
        Map<?, ?> map = request.map();
        if (map == null) {
            throw new IllegalArgumentException(String.format("%s is not a valid username!", username));
        }
        return UUID.fromString(insertDashUUID(map.get("id").toString()));
    }

    @SneakyThrows
    public static List<Friend> friends(UUID uuid) {
        Request request = getRequest(String.format("https://api.namemc.com/profile/%s/friends/", uuid.toString()));
        JsonArray array = request.toJsonArray();
        List<Friend> friends = new ArrayList<>();
        for(JsonElement element : array) {
            JsonObject object = element.getAsJsonObject();
            friends.add(new Friend(UUID.fromString(object.get("uuid").getAsString()), object.get("name").getAsString()));
        }
        return friends;
    }

    @SneakyThrows
    private static Request getRequest(String url) {
        Request request = new Request(url);
        request.prepare();
        request.connect();
        return request;
    }

    /**
     * @author KingTux on SpigotMC
     * @param uuid The UUID of the target
     * @return A String with hyphens inserted
     */
    public static String insertDashUUID(String uuid) {
        StringBuilder sb = new StringBuilder(uuid);
        sb.insert(8, "-");
        sb = new StringBuilder(sb.toString());
        sb.insert(13, "-");
        sb = new StringBuilder(sb.toString());
        sb.insert(18, "-");
        sb = new StringBuilder(sb.toString());
        sb.insert(23, "-");
        return sb.toString();
    }
}
