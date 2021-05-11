package dev.gardeningtool.namemc.profile;

import dev.gardeningtool.namemc.friend.Friend;
import dev.gardeningtool.namemc.web.RequestUtil;
import lombok.Getter;

import java.util.Collection;
import java.util.UUID;

public class Profile {

    private final UUID uuid;
    private final String username;
    private final Collection<Friend> friends;
    private final long cachedTimestamp = System.currentTimeMillis();
    private final boolean hasOptifineCape;

    public Profile(UUID uuid) {
        this.uuid = uuid;
        this.username = RequestUtil.username(uuid);
        this.friends = RequestUtil.friends(uuid);
        this.hasOptifineCape = RequestUtil.optifineCape(username);
    }

    public Profile(String username) {
        this.username = username;
        this.uuid = RequestUtil.uuid(username);
        this.friends = RequestUtil.friends(uuid);
        this.hasOptifineCape = RequestUtil.optifineCape(username);
    }

    public int getFriendsCount() {
        return friends.size();
    }

    public boolean hasOptifineCape() {
        return hasOptifineCape;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getUsername() {
        return username;
    }

    public Collection<Friend> getFriends() {
        return friends;
    }

    public long getCachedTimestamp() {
        return cachedTimestamp;
    }
}
