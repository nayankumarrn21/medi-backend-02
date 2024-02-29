package com.neudesic.MediAssistsPolicyService.services;



import com.neudesic.MediAssistsPolicyService.exceptions.ResourceNotFoundException;
import com.neudesic.MediAssistsPolicyService.modules.Policy;
import com.neudesic.MediAssistsPolicyService.repositories.PolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PolicyService {

    @Autowired
    private PolicyRepository policyRepository;

    public Policy createPolicy(Policy policy){
        return policyRepository.save(policy);
    }

    public List<Policy> getAll(){
        return policyRepository.findAll();
    }
    public Policy getPolicy(Integer pId){
        return policyRepository.findById(pId).orElseThrow(()-> new ResourceNotFoundException("Policy", "id", pId));
    }

    public String deletePolicy(Integer policyId){
        policyRepository.findById(policyId).orElseThrow(()->new ResourceNotFoundException("Policy", "id", policyId));
        policyRepository.deleteById(policyId);
        return "Policy deleted";
    }

}
