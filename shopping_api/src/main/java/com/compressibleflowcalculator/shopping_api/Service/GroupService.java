package com.compressibleflowcalculator.shopping_api.Service;

import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.compressibleflowcalculator.shopping_api.Controller.Responses.Group.InvalidInviteException;
import com.compressibleflowcalculator.shopping_api.Entity.Group;
import com.compressibleflowcalculator.shopping_api.Entity.Group_User;
import com.compressibleflowcalculator.shopping_api.Entity.Invitation;
import com.compressibleflowcalculator.shopping_api.Repository.GroupRepository;
import com.compressibleflowcalculator.shopping_api.Repository.GroupUserRepository;
import com.compressibleflowcalculator.shopping_api.Repository.InviteRepository;

@Service
public class GroupService {

    @Autowired
    private KafkaTemplate<String, Object> template;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private GroupUserRepository groupUserRepository;

    @Autowired
    private InviteRepository inviteRepository;

    public Group NewGroup(Group message, String userid) {

        Group newest_group = groupRepository.save(message);

        Group_User newGroup_Users = Group_User.Make_Group_Users(message, userid, 1);

        newGroup_Users.setGroup(message);

        groupUserRepository.save(newGroup_Users);

        // template.send("groups", -1, null, message.getId().toString(), message);
        // template.send("groups", message);
        CompletableFuture<SendResult<String, Object>> result = template.send("groups", message);
        result.whenComplete((r, ex) -> {
            if (ex == null) {

            } else {
                // Something Better Needs to Happen
                throw new InvalidInviteException(userid);
            }
        });

        return newest_group;
        /*
         * 
         * // template.send("groups", message.getId(), null, message.getId(), message);
         * CompletableFuture<SendResult<String, Object>> result =
         * template.send("groups", message);
         * // result.get();
         * result.whenComplete((r, ex) -> {
         * 
         * });
         * 
         */
    }

    public Group_User AddUser(String userid, String inviteid, String code) {

        Optional<Invitation> invite = inviteRepository.findById(UUID.fromString(inviteid));

        if (!invite.isPresent()) {
            throw new InvalidInviteException("code");
        }

        Group invite_group = invite.get().getGroup();

        ZonedDateTime currentTime = ZonedDateTime.now();

        ZonedDateTime expires = ZonedDateTime.parse(invite.get().getExpires());

        boolean valid = invite.get().VerifyHash(code) && currentTime.isAfter(expires);

        if (!valid) {
            throw new InvalidInviteException(code);
        }

        // Optional<Group> existing_group = groupRepository.findById(groupid);
        // if (invite_group.isPresent()) {

        Group group = invite_group;

        Group_User newGroup_Users = Group_User.Make_Group_Users(group, userid, 1);

        newGroup_Users.setGroup(group);

        groupUserRepository.save(newGroup_Users);

        CompletableFuture<SendResult<String, Object>> result = template.send("newuser", newGroup_Users);
        result.whenComplete((r, ex) -> {
            if (ex == null) {

            } else {
                // Something Better Needs to Happen
                throw new InvalidInviteException(userid);
            }
        });

        /**
         * 
         * Send Group_User Here -> New Group To User...
         */

        // }
        return newGroup_Users;

    }

}
