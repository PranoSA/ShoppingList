package com.compressibleflowcalculator.shopping_api.Service;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compressibleflowcalculator.shopping_api.Controller.Responses.Group.ForbiddenInviteException;
import com.compressibleflowcalculator.shopping_api.Controller.Responses.Group.InviteResponse;
import com.compressibleflowcalculator.shopping_api.Entity.Group;
import com.compressibleflowcalculator.shopping_api.Entity.Group_User;
import com.compressibleflowcalculator.shopping_api.Entity.Invitation;
import com.compressibleflowcalculator.shopping_api.Repository.GroupRepository;
import com.compressibleflowcalculator.shopping_api.Repository.GroupUserRepository;
import com.compressibleflowcalculator.shopping_api.Repository.InviteRepository;

@Service
public class InviteService {
    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private GroupUserRepository groupUserRepository;

    @Autowired
    private InviteRepository inviteRepository;

    public InviteResponse GenerateInvite(String invitee, String groupid) {

        Optional<Group> group_to_invite = groupRepository.findById(UUID.fromString(groupid));

        if (group_to_invite.isEmpty()) {
            throw new ForbiddenInviteException();
        }

        Group invitegroup = group_to_invite.get();

        // Get If Belongs To Group
        List<Group_User> users = groupUserRepository.findByUseridAndGroupId(UUID.fromString(invitee),
                invitegroup.getId());

        if (users.size() == 0) {
            throw new ForbiddenInviteException();
        }

        SecureRandom secureRandom = new SecureRandom();
        byte[] randomData = new byte[32];
        secureRandom.nextBytes(randomData);

        Invitation newinvite = new Invitation(invitegroup, UUID.fromString(invitee), randomData);
        inviteRepository.save(null);

        String pass = Invitation.base64UrlEncode(randomData);

        return new InviteResponse(invitegroup.getId(), newinvite.getId(), pass, newinvite.getExpires());

    }

}
