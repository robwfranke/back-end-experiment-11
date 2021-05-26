package nl.lotrac.bv.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Random;

public final class ExtractUserName {

    public static String ExtractUserNameFromJwt() {
        String userName = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();

        return userName;
    }

}


