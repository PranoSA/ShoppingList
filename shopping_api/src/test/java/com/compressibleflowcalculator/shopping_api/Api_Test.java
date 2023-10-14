package com.compressibleflowcalculator.shopping_api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitTemplateConfigurer;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.compressibleflowcalculator.shopping_api.Controller.GroupController;
import com.compressibleflowcalculator.shopping_api.Entity.Group;
import com.compressibleflowcalculator.shopping_api.Service.GroupService;
import com.compressibleflowcalculator.shopping_api.Service.InviteService;

public class Api_Test {

    String name = "";

    @Autowired
    private GroupService groupService;

    @Autowired
    private InviteService inviteService;

    @MockBean
    RabbitTemplateConfigurer template;

    @BeforeEach
    void setUp() {
        name = "Bob";
    }

    @Test
    public void testerOne() {
        assertTrue(true);
    }

    @Test
    public void testerTwo() {
        assertTrue(false);
    }

    @Test
    public void testerThree() {
        assertEquals("Bob", name);
    }

    @Test
    public void testerFour() {
        // groupService.AddUser(name, name, name)
    }
}
