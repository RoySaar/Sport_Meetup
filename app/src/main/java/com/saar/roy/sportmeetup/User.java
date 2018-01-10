package com.saar.roy.sportmeetup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roy-PC on 08-Jul-17.
 */
public class User {
    public final String uid;
    private final List<String> friends;

    public User(String uid) {
        this.uid = uid;
        friends = new ArrayList<>();
    }

    public List<String> getFriends() {
        return friends;
    }

}
