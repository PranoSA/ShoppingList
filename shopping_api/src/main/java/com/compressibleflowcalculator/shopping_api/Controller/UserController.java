package com.compressibleflowcalculator.shopping_api.Controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.compressibleflowcalculator.shopping_api.Entity.User;

@RestController
public class UserController {

    @RequestMapping(value = "/user/signup", method = RequestMethod.POST)
    public User SignUp(@AuthenticationPrincipal Jwt jwt) {
        String idk = jwt.getClaim("sub");
        System.out.println(idk);
        return new User();
    }

}
