import dev.gardeningtool.namemc.friend.Friend;
import dev.gardeningtool.namemc.profile.Profile;

public class NameMCAPITest {

    public static void main(String[] args) {
        Profile profile = new Profile("lolitsalex");
        profile.getFriends().stream().map(Friend::getUsername).forEach(System.out::println);
        System.out.printf("%s has a total of %d friends!%n", profile.getUsername(), profile.getFriendsCount());
    }
}
