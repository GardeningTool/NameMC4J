# NameMC4J

An unofficial Java based library for the NameMC API. The intention is for this library to be as lightweight and featureful as possible. NameMC4J depends on Gson, however it's not shaded into the JAR.

# Usage

## Obtaining a profile

You can call Profile() in two ways - using a UUID and using the player's username. It's pretty straightforward.
```java
Profile profile = new Profile("Notch");
```
```java
Profile profile = new Profile(UUID.fromString("069a79f4-44e9-4726-a5be-fca90e38aaf5"))
```

## Other methods within the Profile class

The following will output basic information about a profile's friends
```java
Profile profile = new Profile("sadtool");
profile.getFriends().stream().map(Friend::getUsername).forEach(System.out::println);
System.out.printf("%s has a total of %d friends!", profile.getUsername(), profile.getFriendsCount());
```

## Servers

Instantiating a new Server object
```java
Server server = new Server("purpleprison.net");
```

Examples
```Java
Server server = new Server("purpleprison.net");
System.out.println(server.hasLiked("sadtool")) //Prints "true", since sadtool has liked PurplePrison
```