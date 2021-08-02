# NameMC4J

An unofficial Java based library for the NameMC API. The intention is for this library to be as lightweight and featureful as possible. 

This library also includes some features which are not included in NameMC's API, however do relate to the player, such as determining whether or not the profile has an OptiFine cape.

## Dependencies

  NameMC4J only depends on Gson, which is not shaded into the JAR.
  
# Usage

## Obtaining a profile

You can call Profile() in two ways - using a UUID and using the player's username. It's pretty straightforward.
```java
Profile profile = new Profile("Notch");
```
```java
Profile profile = new Profile(UUID.fromString("069a79f4-44e9-4726-a5be-fca90e38aaf5"))
```

## Obtaining friends from a profile

The following will output basic information about a profile's friends
```java
profile.getFriends().stream().map(Friend::getUsername).forEach(System.out::println);
System.out.printf("%s has a total of %d friends!%n", profile.getUsername(), profile.getFriendsCount());
profile.getPreviousNames().forEach((name, timestamp) -> System.out.printf("Name %s %s%n", name, timestamp == 0 ? "is the original IGN" : "was changed at timestamp " + timestamp));
```

## Capes

```java
Profile profile = new Profile("sadtool");
System.out.printf("Does %s have an OptiFine cape? %b!", profile.getUsername(), profile.hasOptifineCape());
```

## Servers

Examples
```Java
Server server = new Server("purpleprison.net");
System.out.println(server.hasLiked("sadtool")) //Prints "true", since sadtool has liked PurplePrison
```
