package com.compressibleflowcalculator.shopping_api.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.compressibleflowcalculator.shopping_api.Entity.Group;
import com.compressibleflowcalculator.shopping_api.Entity.Group_Users;
import com.compressibleflowcalculator.shopping_api.Entity.User;
import com.compressibleflowcalculator.shopping_api.Repository.GroupRepository;
import com.compressibleflowcalculator.shopping_api.Repository.GroupUserRepository;
import com.compressibleflowcalculator.shopping_api.Service.GroupService;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class GroupController {

    @Autowired
    private GroupService groupservice;

    @Autowired
    private KafkaTemplate<String, User> kafkaTemplate;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private GroupUserRepository groupUserRepository;
    // @Autowired
    // private GroupService groupService2

    @RequestMapping(value = "/groups", method = RequestMethod.GET)
    public List<Group> GetGroups() {

        return groupRepository.findAll();

    }

    @RequestMapping(value = "/mygroups", method = RequestMethod.GET)
    public List<Group_Users> getMyGroups() {

        return groupUserRepository.findByUserId("1");
    }

    @RequestMapping(value = "/group", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public Group AddGroup(@RequestBody GroupRequest group) {
        Group newgroup = new Group(group.getName());
        System.out.println(group.getName());
        try {
            groupservice.NewGroup(newgroup);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        Group newestgroup = groupRepository.save(newgroup);

        groupservice.NewGroup(newgroup);

        return newestgroup;
        // return new Group();
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
