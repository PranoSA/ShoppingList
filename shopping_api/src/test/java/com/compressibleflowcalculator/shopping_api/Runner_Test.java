package com.compressibleflowcalculator.shopping_api;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.autoconfigure.amqp.RabbitTemplateConfigurer;
import org.springframework.boot.autoconfigure.amqp.RabbitTemplateCustomizer;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.orm.hibernate5.SpringSessionContext;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.JwtRequestPostProcessor;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.JwtRequestPostProcessor;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.compressibleflowcalculator.shopping_api.Entity.Group;
import com.compressibleflowcalculator.shopping_api.Controller.CustomController;
import com.compressibleflowcalculator.shopping_api.Controller.GroupController;
import com.compressibleflowcalculator.shopping_api.Repository.GroupRepository;
import com.compressibleflowcalculator.shopping_api.Service.GroupService;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.apache.kafka.common.security.TestSecurityConfig;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

//@RunWith(MockitoJUnitRunner.class)
//@ExtendWith(SpringExtension.class)
//@DataJpaTest
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@WithMockUser
//@AutoConfigureMockMvc
//@SpringBootTest(classes = { ShoppingApiApplication.class }, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

@SpringBootTest
@AutoConfigureMockMvc

public class Runner_Test {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MockMvc yourMockMvc;

    // @MockBean
    // private JwtDecoder jwtDecoder;

    @Autowired
    private CustomController myController;

    @InjectMocks
    private GroupController yourController;

    @InjectMocks
    private CustomController myController2;

    @Mock
    private GroupService yourService;

    @Mock
    private GroupRepository yourRepository;

    // @MockBean
    // private JwtDecoder jwtDecoder;

    /*
     * @MockBean
     * private RabbitTemplateConfigurer template;
     * 
     * @MockBean
     * private RabbitTemplateCustomizer templater;
     */

    // @Autowired
    // private GroupRepository realRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup(WebApplicationContext wac) {

        MockitoAnnotations.openMocks(this);
        // MockMvcBuilders.standaloneSetup(null)
        mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();

        // mockMvc = MockMvcBuilders.standaloneSetup(yourController).build();
        // mockMvc = MockMvcBuilders.standaloneSetup(yourController,
        // myController).build();
        // mockMvc = MockMvcBuilders.standaloneSetup(myController2,
        // yourController).build();

    }

    @Test
    public void LetsSee() {

        assertTrue(false);

        // Group isgroup = yourRepository.save(new Group("Bo b", "Lebron"));

        /*
         * Group isgroup = realRepository.save(new Group("Bob", "Lebron"));
         * 
         * assertEquals("Bob", isgroup.getName());
         * 
         * List<Group> groups = realRepository.findAll();
         * 
         * 
         * assertTrue(groups.size() == 1);
         */

    }

    @Test
    public void LetUsAllSee() {

        try {

            // Headers Can Not Be Empty -> Wrong JWT Creation

            // Constructo Can Not Be Instantiated

            // JwtRequestPostProcessor jwt = SecurityMockMvcRequestPostProcessors.jwt();
            // jwt.jwt(Jwt.withTokenValue("token").subject("123151-23-2152-123123").claim("Scope",
            // "Bob").build());

            yourMockMvc.perform(MockMvcRequestBuilders
                    // .get("/groups")
                    .get("/whoami")
                    // .with(SecurityMockMvcRequestPostProcessors.jwt().jwt(jwt -> jwt.claim("Sub",
                    // "Builder").build()))
                    // .with(jwt)
                    // this.mockMvc.
                    // .with(SecurityMockServerConfigurers.)
                    .with(SecurityMockMvcRequestPostProcessors.jwt())
                    .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
        } catch (Exception e) {
            System.out.println("Caught Error Test Mock");
            System.out.println(e.getMessage());
            assertTrue(false);
        }
    }

    @Test
    // @WithMockUser(username = "Bob", roles = { "USER", "ADMIN" })
    public void LetYouSeeAll() {

        try {
            // JwtRequestPostProcessor jwttoken =
            // SecurityMockMvcRequestPostProcessors.jwt();
            // jwttoken.jwt(Jwt.withTokenValue("token").subject("123151-23-2152-123123").claim("Scope",
            // "Bob").build());
            mockMvc.perform(MockMvcRequestBuilders
                    .get("/whoami")
                    // .contentType(MediaType.ALL)
                    // .header("Authorization",
                    // "Bearer
                    // eyJhbGciOiJSUzI1Ni
                    // IsInR5cCIgOiAiSldUIiwia2lkIiA6ICJNZy01RlZ2dDk5VHc4LWhQMjBoT3NhNjhNVXpBZTRpUFRkZjdndEY4TUcwIn0.eyJleHAiOjE2OTY4ODU2MzAsImlhdCI6MTY5Njg4NTMzMCwiYXV0aF90aW1lIjoxNjk2ODg1MzI5LCJqdGkiOiI5MGIwODhiMi03NTc3LTQwNzAtOWU2ZS1kMmM3NmIxNTRiYzciLCJpc3MiOiJodHRwczovL2F1dGguY29tcHJlc3NpYmxlZmxvd2NhbGN1bGF0b3IuY29tL3JlYWxtcy9zaG9wcGluZ2xpc3QiLCJhdWQiOiJ1c2VycyIsInN1YiI6ImY0YTYwMGJhLTU5NGEtNDNkMi05N2JiLWFmM2NhNWY2MDNjMCIsInR5cCI6IklEIiwiYXpwIjoidXNlcnMiLCJub25jZSI6IjA1YTg4NjgzLThmMGUtNDgzZC1iMzBjLWU2YmQ2ZTdjMzliNCIsInNlc3Npb25fc3RhdGUiOiJiZjViZWYzNy1kNWI3LTQ1NTQtYTRiNi0xZGY5YmQxY2Q0YzciLCJhdF9oYXNoIjoiY05jZzF1UU9UUnA4VTNaVXZ5Z0djQSIsImFjciI6IjEiLCJzaWQiOiJiZjViZWYzNy1kNWI3LTQ1NTQtYTRiNi0xZGY5YmQxY2Q0YzciLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsIm5hbWUiOiJQcmFubyBTeXN0ZW1zIiwicHJlZmVycmVkX3VzZXJuYW1lIjoicHJhbm9zYSIsImdpdmVuX25hbWUiOiJQcmFubyIsImZhbWlseV9uYW1lIjoiU3lzdGVtcyIsImVtYWlsIjoicGNhZGxlckBnbWFpbC5jb20ifQ.KcImQCVyYJQkND6-STpA1NgWHjgRZVdMqkm2pZJHvrB7zP0DPQ1hmEkrgQ405UUxHk8xVgd_FfnXVfSg-qUefixw58Ui_Atr5TFRrOZujHRlrVPBTF3rDLNL5i7hMgHHSAodR2REXlx4_dNI084K_q-up3I6FFIzEpn5QdmST-eEMh0nJzwTbnJfMR4D7VMDQ69rfWPOgiXNEZdmNIlLL_sI53E5UpUM_ue1q7uBrq6jjRFvqLgWwnag7QZ9fZgALnxN_AgmwmiHyGI5Y2gCgr6f1YDgSVfv1hAC9HZOhFYWUYPEO1GJ14CcIEZYh1IrjBtCGC0ONQrA-lcYiZLpqQ")

                    .with(SecurityMockMvcRequestPostProcessors.jwt())
                    .contentType(MediaType.APPLICATION_JSON)
            // .with(jwt)
            // .with(SecurityMockMvcRequestPostProcessors.jwt(). )

            ).andExpect(status().isOk());

        } catch (Exception e) {

            System.out.println("Caught Message");
            System.out.println(e.getMessage());
            assertTrue(false);

        }

        // ...
    }
}