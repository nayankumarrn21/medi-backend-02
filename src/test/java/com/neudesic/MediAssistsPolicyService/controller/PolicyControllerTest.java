package com.neudesic.MediAssistsPolicyService.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neudesic.MediAssistsPolicyService.modules.Policy;
import com.neudesic.MediAssistsPolicyService.services.PolicyService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
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

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


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
        given(policyService.createPolicy(Mockito.any()))
                .willAnswer((invocationOnMock -> invocationOnMock.getArgument(0)));

        ResultActions resultActions = mockMvc.perform(post("/policy")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(policy)));
        resultActions.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getAllPolicyTest() throws Exception{
        when(policyService.getAll()).thenReturn(Arrays.asList(policy));

        ResultActions resultActions = mockMvc.perform(get("/policy/list")
                .contentType(MediaType.APPLICATION_JSON));

        resultActions.andExpect(MockMvcResultMatchers.status().isOk())

        .andExpect(MockMvcResultMatchers.jsonPath(("$[0].id")).value(1));
        ObjectMapper objectMapper = new ObjectMapper();
        List<Policy> policies = objectMapper.readValue(resultActions.andReturn().getResponse().getContentAsString(), new TypeReference<List<Policy>>() {});
        Assertions.assertEquals(1, policies.size());
    }

    @Test
    public void deletePolicyTest() throws Exception {
        Integer id = 1;
        when(policyService.deletePolicy(id)).thenReturn("Policy deleted");
        ResultActions resultActions = mockMvc.perform(delete("/policy/1")
                .contentType(MediaType.APPLICATION_JSON));
        resultActions.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
        String response = resultActions.andReturn().getResponse().getContentAsString();
        Assertions.assertEquals("Policy deleted", response);
    }

    @Test
    public void getPolicyTest() throws Exception{
        Integer id = 1;
        when(policyService.getPolicy(id)).thenReturn(policy);
        ResultActions resultActions = mockMvc.perform(get("/policy/1")
                .contentType(MediaType.APPLICATION_JSON));
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andDo(MockMvcResultHandlers.print());
    }
}
