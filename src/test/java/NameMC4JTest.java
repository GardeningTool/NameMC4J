import dev.gardeningtool.namemc.profile.Profile;
import dev.gardeningtool.namemc.server.Server;

import java.util.UUID;

public class NameMC4JTest {

    public static void main(String[] args) {
        Profile profile = new Profile("sadtool");
        Server server = new Server("purpleprison.net");

        System.out.printf("Does %s have an OptiFine cape? %b!%n", profile.getUsername(), profile.hasOptifineCape());
        System.out.printf("Has %s liked %s? %b", profile.getUsername(), server.getIp(), server.hasLiked(profile));

    }
}
