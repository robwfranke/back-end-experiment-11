package nl.lotrac.bv.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Random;

@Slf4j
@Service
public class ExtractUserName {

    public String extractUserNameFromJwt() {


        String userName = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();

        return userName;
    }

}


