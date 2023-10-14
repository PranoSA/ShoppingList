package com.compressibleflowcalculator.shopping_api.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import org.springframework.security.oauth2.jwt.Jwt;

@RestController
public class CustomController {

    @RequestMapping(value = "/csrf", method = RequestMethod.GET)
    public CsrfToken csrf(CsrfToken token) {
        System.out.println("Correct Locat");
        return token;
    }

    @RequestMapping(value = "/whoami", method = RequestMethod.GET)
    public String whoami(@AuthenticationPrincipal Jwt jwt) {
        String sub = jwt.getClaim("sub");
        System.out.println("Submis");
        System.out.println(sub);
        return sub;
    }

    @RequestMapping(value = "/whoami2", method = RequestMethod.GET)
    public String whoami2() {
        return "Sub";
    }

    @RequestMapping(value = "/custom", method = RequestMethod.POST)
    public String custom() {
        if (true) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cause description here");
        }
        return "custom";
    }

}
