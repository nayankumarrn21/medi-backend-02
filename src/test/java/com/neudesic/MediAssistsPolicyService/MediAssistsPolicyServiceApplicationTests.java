package com.neudesic.MediAssistsPolicyService;

import com.neudesic.MediAssistsPolicyService.modules.Policy;
import com.neudesic.MediAssistsPolicyService.repositories.PolicyRepository;
import com.neudesic.MediAssistsPolicyService.services.PolicyService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;

@SpringBootTest
class MediAssistsPolicyServiceApplicationTests {

	@Test
	void contextLoads() {
		System.out.println("This is Testing");
	}

	@Autowired
	private PolicyService policyService;

	@MockBean
	private PolicyRepository policyRepository;


	private static final Policy policy;
	static {
		policy = new Policy();
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
	}
}
