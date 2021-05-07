import dev.gardeningtool.namemc.server.Server;

import java.util.UUID;

public class NameMCAPITest {

    public static void main(String[] args) {
        Server server = new Server("purpleprison.net");
        server.getLikedUsers().stream().map(UUID::toString).forEach(System.out::println);

        System.out.println(server.hasLiked("sadtool"));
    }
}
