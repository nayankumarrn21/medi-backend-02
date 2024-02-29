package com.neudesic.MediAssistsPolicyService.repositories;


import com.neudesic.MediAssistsPolicyService.modules.UserPolicyDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserPolicyRepository extends JpaRepository<UserPolicyDetails, Integer> {
    List<UserPolicyDetails> findByUserId(Integer uid);
}
