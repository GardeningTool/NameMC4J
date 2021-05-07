package dev.gardeningtool.namemc.friend;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter @AllArgsConstructor
public class Friend {

    private final UUID uuid;
    private final String username;
}
