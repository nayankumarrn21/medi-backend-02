package com.neudesic.MediAssistsPolicyService.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neudesic.MediAssistsPolicyService.modules.Policy;
import com.neudesic.MediAssistsPolicyService.services.PolicyService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcResultHandlersDsl;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.stream.Collectors;
import java.util.stream.Stream;


import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@WebMvcTest(controllers = PolicyController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(SpringExtension.class)
public class PolicyControllerTest {


    @MockBean
    private PolicyService policyService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    private static Policy policy;

    @BeforeAll
    public static void init(){
        policy = new Policy();
        policy.setId(1);
        policy.setTitle("My Policy");
        policy.setDescription("Description");
        policy.setCompanyName("Neu");
        policy.setInsuredAmount("384384");
        policy.setBeneficiariesList(Stream.of("Son").collect(Collectors.toList()));
    }

    @Test
    public void createPolicyTest() throws Exception {
        given(policyService.createPolicy(ArgumentMatchers.any()))
                .willAnswer((invocationOnMock -> invocationOnMock.getArgument(0)));

        ResultActions resultActions = mockMvc.perform(post("/policy")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(policy)));
        resultActions.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getAllPolicyTest() throws Exception{
//        given(policyService.getAll()).willAnswer()
    }
}
