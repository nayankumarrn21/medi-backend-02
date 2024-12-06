package com.neudesic.MediAssistsPolicyService.repositories;

import com.neudesic.MediAssistsPolicyService.controller.UserPolicyController;
import com.neudesic.MediAssistsPolicyService.modules.UserPolicyDetails;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserPolicyRepositoryTest {
    @Autowired
    private UserPolicyRepository userPolicyRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void findByUserId() {
        // Arrange
        UserPolicyDetails userPolicyDetails1 = new UserPolicyDetails();
        userPolicyDetails1.setUserId(123);
        userPolicyDetails1.setStartedDate(new Date());
        userPolicyDetails1.setEndingDate(new Date());
        userPolicyDetails1.setBeneficiaries(Arrays.asList("Beneficiary1", "Beneficiary2"));
        userPolicyDetails1.setNominee("Nominee");

        UserPolicyDetails userPolicyDetails2 = new UserPolicyDetails();
        userPolicyDetails2.setUserId(123);
        userPolicyDetails2.setStartedDate(new Date());
        userPolicyDetails2.setEndingDate(new Date());
        userPolicyDetails2.setBeneficiaries(Arrays.asList("Beneficiary3", "Beneficiary4"));
        userPolicyDetails2.setNominee("Nominee2");

        entityManager.persist(userPolicyDetails1);
        entityManager.persist(userPolicyDetails2);
        entityManager.flush();
        List<UserPolicyDetails> result = userPolicyRepository.findByUserId(123);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(123, result.get(0).getUserId());
        assertEquals(123, result.get(1).getUserId());
    }

    @Test
    void testFindByUserIdWithNoData() {
        List<UserPolicyDetails> result = userPolicyRepository.findByUserId(999);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

}
