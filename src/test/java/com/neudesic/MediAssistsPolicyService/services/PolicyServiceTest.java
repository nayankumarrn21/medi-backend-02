package com.neudesic.MediAssistsPolicyService.services;

import com.neudesic.MediAssistsPolicyService.modules.Policy;
import com.neudesic.MediAssistsPolicyService.repositories.PolicyRepository;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;


@ExtendWith(SpringExtension.class)
public class PolicyServiceTest {

    @Mock
    private PolicyRepository policyRepository;

    @InjectMocks
    private PolicyService policyService;

    private static Policy policy;

    @BeforeEach
    public void ini() {
        policy = new Policy();
        policy.setId(1);
        policy.setTitle("My Policy");
        policy.setDescription("Description");
        policy.setCompanyName("Neu");
        policy.setInsuredAmount("384384");
        policy.setBeneficiariesList(Stream.of("Son").collect(Collectors.toList()));
    }

    @Test
    public void createPolicyTest(){
        when(policyRepository.save(policy)).thenReturn(policy);
        Policy policy1 = policyService.createPolicy(policy);
        Assertions.assertNotNull(policy1);
        Assertions.assertTrue(policy1.getId()>0);
    }

    @Test
    public void getAllPolicyTest(){
        Policy policy2 = policy.clone();
        policy2.setId(2);
        when(policyRepository.findAll()).thenReturn(Stream.of(policy, policy2 ).collect(Collectors.toList()));

        List<Policy> policyList = policyService.getAll();
        Assertions.assertNotNull(policyList);
        Assertions.assertEquals(2, policyList.size());
    }


    @Test
    public void getPolicyByIdTest1() {
        Integer id = 1;
        when(policyRepository.findById(Mockito.any(Integer.class))).thenReturn(Optional.of(policy));
        Policy policy1 = policyService.getPolicy(id);
        Mockito.verify(policyRepository, Mockito.times(1)).findById(Mockito.eq(id));
        Assertions.assertNotNull(policy1);
        Assertions.assertEquals(policy1.getId(), policy.getId());
    }

    @Test
    public void deletePolicyTest(){
        Integer id =1;
        when(policyRepository.findById(Mockito.any(Integer.class))).thenReturn(Optional.of(policy));
        policyService.deletePolicy(id);
        verify(policyRepository).deleteById(id);
    }
}
