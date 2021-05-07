package dev.gardeningtool.namemc.server;

import dev.gardeningtool.namemc.profile.Profile;
import dev.gardeningtool.namemc.web.Request;
import dev.gardeningtool.namemc.web.RequestUtil;
import lombok.Getter;

import java.util.Collection;
import java.util.UUID;

@Getter
public class Server {

    private final Collection<UUID> likedUsers;

    public Server(String ip) {
        Request request = new Request(String.format("https://api.namemc.com/server/%s/likes", ip));
        request.prepare();
        request.connect();

        this.likedUsers = RequestUtil.getLikes(request);
    }

    public boolean hasLiked(String username) {
        UUID uuid = RequestUtil.uuid(username);
        return likedUsers.contains(uuid);
    }

    public boolean hasLiked(UUID uuid) {
        return likedUsers.contains(uuid);
    }

    public boolean hasLiked(Profile profile) {
        return hasLiked(profile.getUuid());
    }


}
