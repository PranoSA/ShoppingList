package com.compressibleflowcalculator.shopping_api.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.kafka.common.errors.GroupAuthorizationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compressibleflowcalculator.shopping_api.Controller.Responses.Lists.ItemNotFoundException;
import com.compressibleflowcalculator.shopping_api.Controller.Responses.Lists.ListNotFoundException;
import com.compressibleflowcalculator.shopping_api.Entity.Group;
import com.compressibleflowcalculator.shopping_api.Entity.Group_User;
import com.compressibleflowcalculator.shopping_api.Entity.Item;
import com.compressibleflowcalculator.shopping_api.Entity.ShoppingList;
import com.compressibleflowcalculator.shopping_api.Repository.GroupRepository;
import com.compressibleflowcalculator.shopping_api.Repository.GroupUserRepository;
import com.compressibleflowcalculator.shopping_api.Repository.ItemRepository;
import com.compressibleflowcalculator.shopping_api.Repository.ListRepository;

@Service
public class ListService {

    @Autowired
    private ListRepository listRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private GroupUserRepository groupUserRepository;

    @Autowired
    private GroupRepository groupRepository;

    private boolean authorizedForList(UUID groupid, UUID userid) {

        return true;
    }

    public ShoppingList newList(UUID userid, UUID groupid, String name, ZonedDateTime date) {

        List<Group_User> user = groupUserRepository.findByUseridAndGroupId(groupid, userid);

        if (user.size() == 0) {
            throw new ListNotFoundException();
        }

        Group_User usergroup = user.get(0);

        Group group = usergroup.getGroup();
        ShoppingList newlist = new ShoppingList(group, name, userid, date);

        listRepository.save(newlist);

        return newlist;

    }

    public List<Item> getListItems(UUID listid, UUID userid) {
        Optional<ShoppingList> shoplist = listRepository.findById(listid);

        if (!shoplist.isPresent()) {
            throw new ListNotFoundException();
        }

        List<Group_User> user = groupUserRepository.findByUseridAndGroupId(userid, shoplist.get().getGroup().getId());

        if (user.size() == 0) {
            throw new ListNotFoundException();
            /*
             * throw new GroupAuthorizationException("not Part Of The Group",
             * shoplist.get().getGroup().getId().toString());
             */
        }
        // Group shoppinglist = shoplist.get().getGroup();

        return shoplist.get().getItems();

    }

    public Item addItemToList(UUID listid, UUID userid, int quantity, int size, String name) {

        Optional<ShoppingList> shoplist = listRepository.findById(listid);

        if (!shoplist.isPresent()) {
            throw new ListNotFoundException();
        }

        List<Group_User> user = groupUserRepository.findByUseridAndGroupId(userid, shoplist.get().getGroup().getId());

        if (user.size() == 0) {
            throw new ListNotFoundException(); /*
                                                * GroupAuthorizationException("not Part Of The Group",
                                                * shoplist.get().getGroup().getId().toString());
                                                */
        }

        Item newItem = new Item(userid, shoplist.get(), quantity, size, name);

        itemRepository.save(newItem);

        return newItem;
    }

    public List<ShoppingList> getListsByGroup(UUID userid, UUID groupid) {

        List<Group_User> user = groupUserRepository.findByUseridAndGroupId(userid, groupid);

        if (user.size() == 0) {
            throw new ListNotFoundException(); /*
                                                * GroupAuthorizationException("not Part Of The Group",
                                                * shoplist.get().getGroup().getId().toString());
                                                */
        }

        List<ShoppingList> shoppingLists = listRepository.findByGroupId(groupid);

        return shoppingLists;
    }

    public Item deleteItemFromList(UUID listid, UUID userid, UUID itemid) {
        Optional<ShoppingList> shoplist = listRepository.findById(listid);

        if (!shoplist.isPresent()) {
            throw new ListNotFoundException();
        }

        List<Group_User> user = groupUserRepository.findByUseridAndGroupId(shoplist.get().getGroup().getId(), userid);

        if (user.size() == 0) {
            throw new ListNotFoundException();/*
                                               * GroupAuthorizationException("not Part Of The Group",
                                               * shoplist.get().getGroup().getId().toString());
                                               */
        }

        Optional<Item> item_to_delete = itemRepository.findById(itemid);

        if (!item_to_delete.isPresent()) {
            throw new ItemNotFoundException();

        }

        if (!userid.equals(item_to_delete.get().getOwner())) {
            throw new GroupAuthorizationException("Can't Access This Item");
        }

        itemRepository.delete(item_to_delete.get());

        return item_to_delete.get();

    }

}
