package dev.gardeningtool.namemc.profile;

import dev.gardeningtool.namemc.friend.Friend;
import dev.gardeningtool.namemc.web.RequestUtil;
import lombok.Getter;

import java.util.Collection;
import java.util.UUID;

@Getter
public class Profile {

    private final UUID uuid;
    private final String username;
    private final Collection<Friend> friends;
    private final long cachedTimestamp = System.currentTimeMillis();

    public Profile(UUID uuid) {
        this.uuid = uuid;
        this.username = RequestUtil.username(uuid);
        this.friends = RequestUtil.friends(uuid);
    }

    public Profile(String username) {
        this.username = username;
        this.uuid = RequestUtil.uuid(username);
        this.friends = RequestUtil.friends(uuid);
    }

    public int getFriendsCount() {
        return friends.size();
    }

}
