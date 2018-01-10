package com.saar.roy.sportmeetup;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Eidan on 7/10/2017.
 */

class BackEnd implements BackendInterface {

    public static final Pattern VALID_EMAIL_ADDRESS =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    @Override
    public boolean signUp(String username, String password) {
        return false;
    }

    @Override
    public boolean signIn(String username, String password) {
        return username.equals(username) && password.equals(password);
    }

    @Override
    public void saveMatch(Match match) {

    }

    @Override
    public void saveUser(User user) {

    }

    @Override
    public void getMatchesByUser(User user) {

    }

    public static Verification verifyEmailAndPassword(String email, String password) {
        if (email == null || email.equals("") || password == null || email.equals(""))
            // Email or password or empty
            return Verification.EITHER_IS_NULL;
      //  if (password.length() > 8)
            // Password is too short
           // return Verification.PASSWORD_TOO_SHORT;
        Matcher matcher = VALID_EMAIL_ADDRESS.matcher(email);
        if (!matcher.find())
            //Invalid email adress
            return Verification.EMAIL_NOT_VALID;
        else
            //All is valid
            return Verification.VALID;
    }
}
