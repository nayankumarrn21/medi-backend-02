package com.neudesic.MediAssistsPolicyService.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.neudesic.MediAssistsPolicyService.dto.UserPolicyDto;
import com.neudesic.MediAssistsPolicyService.modules.Policy;
import com.neudesic.MediAssistsPolicyService.modules.UserPolicyDetails;
import com.neudesic.MediAssistsPolicyService.services.PolicyService;
import com.neudesic.MediAssistsPolicyService.services.UserPolicyService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(controllers = UserPolicyController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(SpringExtension.class)
public class UserPolicyControllerTest {
    @MockBean
    private UserPolicyService userPolicyService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    private static UserPolicyDetails userPolicyDetails;

    @Test
    void createUserPolicy() throws Exception {

        UserPolicyDto userPolicyDto = new UserPolicyDto();
        UserPolicyDetails userPolicyDetails = new UserPolicyDetails();
        userPolicyDetails.setId(1);

        Mockito.when(userPolicyService.create(Mockito.any(UserPolicyDto.class))).thenReturn(userPolicyDetails);

        mockMvc.perform(post("/user-policy")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(userPolicyDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.id").value(1));

    }

    @Test
    void getListOfUserDetails() throws Exception {
        UserPolicyDetails userPolicyDetails1 = new UserPolicyDetails();
        userPolicyDetails1.setId(1);
        userPolicyDetails1.setUserId(123);

        UserPolicyDetails userPolicyDetails2 = new UserPolicyDetails();
        userPolicyDetails2.setId(2);
        userPolicyDetails2.setUserId(456);

        List<UserPolicyDetails> userPolicyDetailsList = Arrays.asList(userPolicyDetails1, userPolicyDetails2);

        Mockito.when(userPolicyService.getListOfUserDetails(Mockito.anyInt())).thenReturn(userPolicyDetailsList);

        mockMvc.perform(get("/user-policy/user/123"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$[0].userId").value(123))
                .andExpect(jsonPath("$[1].userId").value(456));
    }


}
