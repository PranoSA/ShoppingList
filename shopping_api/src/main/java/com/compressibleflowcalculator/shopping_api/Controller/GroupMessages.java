package com.compressibleflowcalculator.shopping_api.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.compressibleflowcalculator.shopping_api.Entity.Group;
import com.compressibleflowcalculator.shopping_api.Entity.ShoppingList;

@CrossOrigin(maxAge = 150000, allowedHeaders = "*", origins = { "http://localhost:3000", "https://localhost:5173",
        "http://localhost:3000/", "http://localhost:5173" }, methods = {
                RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS,
                RequestMethod.POST })
@Controller
public class GroupMessages {
    // ShoppingList message

    @SendTo("/topic/{groupid}")
    @MessageMapping("/notifications/{groupid}")
    String GroupMessage(@DestinationVariable String groupid,
            @Payload String message) {

        System.out.println("Got Message");

        // String uuid = jwt.getClaim("sub");
        // System.out.println(uuid);

        System.out.println(groupid);
        System.out.println(message);
        // String dynamicTopic = "/topic/group/" + groupid;

        // System.out.println("Got Something");

        // template.convertAndSend(dynamicTopic, message);

        return message;

    }

    @SendTo("/topic/list.{groupid}")
    @MessageMapping("/notifications/list/{groupid}")
    String NotificationForGroupListMessage(@DestinationVariable String groupid, @Payload String message) {

        System.out.println("UUID Young Lad");
        // String uuid = jwt.getClaim("sub");
        // System.out.println(uuid);
        return message;
    }

}
