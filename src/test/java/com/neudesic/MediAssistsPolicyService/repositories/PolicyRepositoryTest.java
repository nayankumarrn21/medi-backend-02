package com.neudesic.MediAssistsPolicyService.repositories;


import com.neudesic.MediAssistsPolicyService.modules.Policy;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class PolicyRepositoryTest {
    private static Policy policy;

    @BeforeAll
    public static void init(){
        policy = new Policy();
        policy.setTitle("My Policy");
        policy.setDescription("Description");
        policy.setCompanyName("Neu");
        policy.setInsuredAmount("384384");
        policy.setBeneficiariesList(Stream.of("Son").collect(Collectors.toList()));
    }

    @Autowired
    private PolicyRepository policyRepository;

    @Test
    public void saveTest(){
        Policy policyInDb = policyRepository.save(policy);
        System.out.println(policyInDb);
        Assertions.assertNotNull(policyInDb);
        Assertions.assertTrue(policyInDb.getId()>0);
    }

    @Test
    public void getAllPolicyTest(){
        policyRepository.save(policy);
        policyRepository.save(policy);
        List<Policy> policyList = policyRepository.findAll();
        System.out.println(policyList);
        Assertions.assertNotNull(policyList);
        Assertions.assertFalse(policyList.isEmpty());
    }

    @Test
    public void getPolicyById(){
        Policy policy1 = policyRepository.save(policy);
        Assertions.assertNotNull(policy1);
        Policy policy2 = policyRepository.findById(policy1.getId()).orElse(null);
        Assertions.assertNotNull(policy2);
        Assertions.assertEquals(policy1.getId(), policy2.getId());
    }

    @Test
    public void deletePolicy(){
        Policy policy1 = policyRepository.save(policy);
        policyRepository.deleteById(policy1.getId());
        Policy policy2 = policyRepository.findById(policy1.getId()).orElse(null);
        Assertions.assertNull(policy2);
    }
}
