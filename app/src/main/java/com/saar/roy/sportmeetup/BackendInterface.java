package com.saar.roy.sportmeetup;

/**
 * Created by Roy-PC on 08-Jul-17.
 */
public interface BackendInterface {
    boolean signUp(String username, String password);
    boolean signIn(String username, String password);
    void saveMatch(Match match);
    void saveUser(User user);
    void getMatchesByUser(User user);
}
