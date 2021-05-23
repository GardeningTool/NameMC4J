import dev.gardeningtool.namemc.profile.Profile;
import dev.gardeningtool.namemc.server.Server;

import java.util.Map;

public class NameMC4JTest {

    public static void main(String[] args) {
        Profile profile = new Profile("sadtool");
        Server server = new Server("purpleprison.net");

        Map<String, Long> map = profile.getPreviousNames();
        System.out.printf("Let's see %s's, previous names!", profile.getUsername());

        for(Map.Entry<String, Long> entry : map.entrySet()) {
            System.out.printf("%s : %s%n", entry.getKey(), entry.getValue());
        }

        System.out.printf("Does %s have an OptiFine cape? %b!%n", profile.getUsername(), profile.hasOptifineCape());
        System.out.printf("Has %s liked %s? %b", profile.getUsername(), server.getIp(), server.hasLiked(profile));

        map.forEach((name, timestamp) -> System.out.printf("Name %s %s%n", name, timestamp == 0 ? "is the original IGN" : "was changed at timestamp " + timestamp));
    }
}
