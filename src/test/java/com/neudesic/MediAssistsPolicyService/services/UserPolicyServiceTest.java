package com.neudesic.MediAssistsPolicyService.services;


import com.neudesic.MediAssistsPolicyService.controller.UserPolicyController;
import com.neudesic.MediAssistsPolicyService.dto.UserPolicyDto;
import com.neudesic.MediAssistsPolicyService.modules.Policy;
import com.neudesic.MediAssistsPolicyService.modules.UserPolicyDetails;
import com.neudesic.MediAssistsPolicyService.repositories.UserPolicyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class UserPolicyServiceTest {
    @Mock
    private UserPolicyRepository userPolicyRepository;

    @Mock
    private PolicyService policyService;

    @InjectMocks
    private UserPolicyService userPolicyService;

    @Test
    void create() {
        UserPolicyDto userPolicyDto = new UserPolicyDto();
        userPolicyDto.setPolicyId(1);

        UserPolicyDetails userPolicyDetails = new UserPolicyDetails();
        userPolicyDetails.setUserId(123);
        userPolicyDetails.setStartedDate(new Date());
        userPolicyDetails.setEndingDate(new Date());
        userPolicyDetails.setBeneficiaries(Arrays.asList("Beneficiary1", "Beneficiary2"));
        userPolicyDetails.setNominee("Nominee");
        userPolicyDto.setUserPolicyDetails(userPolicyDetails);

        Policy policy = new Policy();
        policy.setId(1);
        when(policyService.getPolicy(userPolicyDto.getPolicyId())).thenReturn(policy);
        when(userPolicyRepository.save(any(UserPolicyDetails.class))).thenReturn(userPolicyDetails);

        UserPolicyDetails result = userPolicyService.create(userPolicyDto);

        assertNotNull(result);
        assertEquals(userPolicyDetails.getUserId(), result.getUserId());
        assertEquals(userPolicyDetails.getNominee(), result.getNominee());
        verify(userPolicyRepository, times(1)).save(any(UserPolicyDetails.class));
    }

    @Test
    void getListOfUserDetails_NoPolicies() {
        when(userPolicyRepository.findByUserId(999)).thenReturn(Collections.emptyList());
        List<UserPolicyDetails> result = userPolicyService.getListOfUserDetails(999);
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(userPolicyRepository, times(1)).findByUserId(999);
    }


    @Test
    void testGetListOfUserDetails() {
        UserPolicyDetails userPolicyDetails1 = new UserPolicyDetails();
        userPolicyDetails1.setUserId(123);
        userPolicyDetails1.setStartedDate(new Date());
        userPolicyDetails1.setEndingDate(new Date());
        userPolicyDetails1.setBeneficiaries(List.of("Beneficiary1"));
        userPolicyDetails1.setNominee("Nominee");

        UserPolicyDetails userPolicyDetails2 = new UserPolicyDetails();
        userPolicyDetails2.setUserId(123);
        userPolicyDetails2.setStartedDate(new Date());
        userPolicyDetails2.setEndingDate(new Date());
        userPolicyDetails2.setBeneficiaries(List.of("Beneficiary2"));
        userPolicyDetails2.setNominee("Nominee2");

        List<UserPolicyDetails> userPolicyDetailsList = Arrays.asList(userPolicyDetails1, userPolicyDetails2);

        when(userPolicyRepository.findByUserId(123)).thenReturn(userPolicyDetailsList);

        List<UserPolicyDetails> result = userPolicyService.getListOfUserDetails(123);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(userPolicyDetails1.getUserId(), result.get(0).getUserId());
        assertEquals(userPolicyDetails2.getUserId(), result.get(1).getUserId());
        verify(userPolicyRepository, times(1)).findByUserId(123);
    }

}
