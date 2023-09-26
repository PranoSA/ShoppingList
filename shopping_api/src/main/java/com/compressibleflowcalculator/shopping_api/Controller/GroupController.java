package com.compressibleflowcalculator.shopping_api.Controller;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.compressibleflowcalculator.shopping_api.Controller.Requests.GroupRequest;
import com.compressibleflowcalculator.shopping_api.Controller.Responses.Group.InviteResponse;
import com.compressibleflowcalculator.shopping_api.Entity.Group;
import com.compressibleflowcalculator.shopping_api.Entity.Group_User;
import com.compressibleflowcalculator.shopping_api.Entity.Invitation;
import com.compressibleflowcalculator.shopping_api.Repository.GroupRepository;
import com.compressibleflowcalculator.shopping_api.Repository.GroupUserRepository;
import com.compressibleflowcalculator.shopping_api.Repository.InviteRepository;
import com.compressibleflowcalculator.shopping_api.Service.GroupService;
import com.compressibleflowcalculator.shopping_api.Service.InviteService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin(maxAge = 7200, allowedHeaders = "*", origins = {
        "http://localhost:3000" }, methods = {
                RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS, RequestMethod.POST })
public class GroupController {

    @Autowired
    private GroupService groupservice;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private GroupUserRepository groupUserRepository;

    @Autowired
    private InviteService inviteService;

    // @Autowired
    // private GroupService groupService2
    @CrossOrigin(allowedHeaders = "*", origins = { "http://localhost:3000",
            "http://localhost:3000/" }, methods = {
                    RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS,
                    RequestMethod.POST })

    /*
     * 
     * Should Be Admin Only
     * 
     * @RequestMapping(value = "/groups", method = RequestMethod.GET)
     * public List<Group> GetGroups() {
     * 
     * return groupRepository.findAll();
     * 
     * }
     */

    /**
     * Get Request For Your Groups
     * 
     * @return
     */

    /*
     * Get All Of Your Groups
     */
    @RequestMapping(value = "/mygroups", method = RequestMethod.GET)
    public List<Group> getMyGroups(@AuthenticationPrincipal Jwt jwt) {

        String userid = jwt.getClaim("sub");

        List<Group_User> mygroups = groupUserRepository.findByUserid(UUID.fromString(userid));

        List<Group> groups = mygroups.stream()
                .map(groupuser -> groupuser.getGroup())
                .collect(Collectors.toList());

        return groups;
    }

    /**
     * Create a New Group...
     * 
     * Max is Number 10
     * 
     * Publish To Kafka For Consistency Between Micoservices
     * 
     * @param group
     * @param jwt
     * @return
     */

    @RequestMapping(value = "/group", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public Group AddGroup(@RequestBody GroupRequest group, @AuthenticationPrincipal Jwt jwt) {
        Group newgroup = new Group(group.getName(), group.getDescription());
        String idk = jwt.getClaim("sub");
        Group newestgroup;
        try {
            newestgroup = groupservice.NewGroup(newgroup, idk);
            return newestgroup;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return new Group();

    }

    /*
     * @RequestMapping(value = "/group/:groupid", method = RequestMethod.POST,
     * produces = "application/json")
     * public Invitation InviteUser(@AuthenticationPrincipal Jwt jwt) {
     * // Ensure The Creator Is Part of the Group ...
     * 
     * String user = jwt.getClaim("sub");
     * 
     * Invitation invite = new Invitation();
     * 
     * return invite;
     * }
     */

    @RequestMapping(value = "/group/:groupid/invite", method = RequestMethod.POST, produces = "application/json")
    public InviteResponse GenerateInvite(@AuthenticationPrincipal Jwt jwt, @PathVariable String groupid) {

        String userid = jwt.getClaim("sub");

        InviteResponse invite = inviteService.GenerateInvite(userid, groupid);

        return invite;

    }

    /**
     * 
     * 
     * @param jwt
     * @param inviteid
     * @param code
     * @return
     */
    @RequestMapping(value = "/group/:groupid/invite/:inviteid", method = RequestMethod.POST, produces = "application/json")
    public Group_User AcceptInvite(@AuthenticationPrincipal Jwt jwt, @PathVariable String inviteid,
            @RequestParam String code) {

        String idk = jwt.getClaim("sub");
        Group_User user = groupservice.AddUser(idk, inviteid, code);

        return user;
    }

    /*
     * private class GroupRequest {
     * private String Name;
     * 
     * public GroupRequest() {
     * 
     * }
     * 
     * public String getName() {
     * return Name;
     * }
     * }
     */

}
