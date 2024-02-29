package com.neudesic.MediAssistsPolicyService.dto;


import com.neudesic.MediAssistsPolicyService.modules.UserPolicyDetails;

public class UserPolicyDto {
    private UserPolicyDetails userPolicyDetails;

    private Integer policyId;

    private Integer userId;

    public UserPolicyDetails getUserPolicyDetails() {
        return userPolicyDetails;
    }

    public void setUserPolicyDetails(UserPolicyDetails userPolicyDetails) {
        this.userPolicyDetails = userPolicyDetails;
    }

    public Integer getPolicyId() {
        return policyId;
    }

    public void setPolicyId(Integer policyId) {
        this.policyId = policyId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
