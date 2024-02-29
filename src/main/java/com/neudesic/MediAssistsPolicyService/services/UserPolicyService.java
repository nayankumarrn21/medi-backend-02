package com.neudesic.MediAssistsPolicyService.services;



import com.neudesic.MediAssistsPolicyService.dto.UserPolicyDto;
import com.neudesic.MediAssistsPolicyService.modules.Policy;
import com.neudesic.MediAssistsPolicyService.modules.UserPolicyDetails;
import com.neudesic.MediAssistsPolicyService.repositories.UserPolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserPolicyService {


    @Autowired
    private UserPolicyRepository userPolicyRepository;

    @Autowired
    private PolicyService policyService;

    public UserPolicyDetails create(UserPolicyDto userPolicyDto){
        Policy policy = policyService.getPolicy(userPolicyDto.getPolicyId());
        userPolicyDto.getUserPolicyDetails().setPolicy(policy);
        return userPolicyRepository.save(userPolicyDto.getUserPolicyDetails());
    }

    public List<UserPolicyDetails> getListOfUserDetails(Integer uid){
        return userPolicyRepository.findByUserId(uid);
    }

}
