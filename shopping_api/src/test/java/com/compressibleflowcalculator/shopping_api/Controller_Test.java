package com.compressibleflowcalculator.shopping_api;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.Filter;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;

import com.compressibleflowcalculator.shopping_api.Controller.GroupController;
import com.compressibleflowcalculator.shopping_api.Entity.Group;
import com.compressibleflowcalculator.shopping_api.Repository.GroupRepository;
import com.compressibleflowcalculator.shopping_api.Repository.GroupUserRepository;
import com.compressibleflowcalculator.shopping_api.Service.GroupService;
import com.compressibleflowcalculator.shopping_api.Service.InviteService;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;

import javax.print.attribute.standard.Media;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.Before;
import org.aspectj.lang.annotation.Before;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;

/*@ContextConfiguration(classes = {
        FilterChainProxy.class,
        SecurityFilterChain.class
})*/
@WebMvcTest(controllers = GroupController.class)
public class Controller_Test {

    @Autowired
    private MockMvc mockmvc;

    @Autowired
    private ObjectMapper objectMapper; // What is this for?????

    // @InjectMocks
    // private GroupController groupController;
    // @Mock
    // @Autowired
    @MockBean
    private GroupService groupservice;

    @MockBean
    private GroupRepository groupRepository;

    @MockBean
    private GroupUserRepository groupUserRepository;

    @MockBean
    private InviteService inviteService;

    // springSecurity(Filter springSecurityFilterChain
    // @Autowired
    // private Filter springSecurityFilterChain;

    // @MockBean
    // private SecurityFilterChain chain;

    // @MockBean
    // private RegisterUseCase registerUseCase;
    // What is MockBean for???

    @BeforeEach
    public void Setup(ApplicationContext context) {

        System.out.println("Context");
        System.out.println(context.getBeanDefinitionNames());
        // System.out.println(Arrays.asList(context.getBeanDefinitionNames()));
        // MockitoAnnotations.openMocks(this);

        // this.mockmvc = MockMvcBuilders.standaloneSetup(groupController)
        // .apply(SecurityMockMvcConfigurers.springSecurity())

        // .build();

    }

    @Test
    public void Setup2(ApplicationContext context) {

        System.out.println("Context");
        List<String> printer = Arrays.asList(context.getBeanDefinitionNames());
        System.out.println(context.getBeanDefinitionNames());
        System.out.println(printer);
        // System.out.println(Arrays.asList(context.getBeanDefinitionNames()));
        // MockitoAnnotations.openMocks(this);

        // this.mockmvc = MockMvcBuilders.standaloneSetup(groupController)
        // .apply(SecurityMockMvcConfigurers.springSecurity())

        // .build();

    }

    private final String testuserid = "a127c5da-a7d9-4c7c-a623-a067943151f9";

    @Test
    public void LetsTry() {

        try {
            mockmvc.perform(MockMvcRequestBuilders
                    .get("/groups")
                    .with(SecurityMockMvcRequestPostProcessors.jwt()
                            .jwt(jwt -> jwt.claim("sub", testuserid))))
                    .andExpect(status().isOk())

                    .andExpect(jsonPath("$", Matchers.hasSize(0)));
        } catch (Exception e) {

            System.out.println("Caught Message");
            System.out.println(e.getMessage());
            assertTrue(false);

        }
    }

    @Test
    public void AddGroupTry() throws Exception {

        Group newgroup = new Group();

        Mockito.when(groupservice.NewGroup(newgroup, testuserid)).thenReturn(new Group("ted", "SSSD"));
        Mockito.when(groupservice.NewGroup(null, null)).thenReturn(new Group("ted", "SSSD"));
        Mockito.when(groupservice.NewGroup(newgroup, "")).thenReturn(new Group("ted", "SSSD"));
        Mockito.when(groupservice.NewGroup(ArgumentMatchers.any(Group.class),
                anyString()))
                .thenReturn(new Group("ted", "SSSD"));

        String content = objectMapper.writeValueAsString(newgroup);
        // objectMapper.writerWithView(getClass())
        mockmvc.perform(MockMvcRequestBuilders
                .post("/group")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content)
                .with(SecurityMockMvcRequestPostProcessors.jwt()
                        .jwt(jwt -> jwt.claim("sub", testuserid))))
                .andExpect(status().isOk())

                .andExpect(jsonPath("$.name", is("")));
    }
}
