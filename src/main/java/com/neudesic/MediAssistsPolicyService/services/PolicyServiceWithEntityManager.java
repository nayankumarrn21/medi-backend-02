package com.neudesic.MediAssistsPolicyService.services;


import com.neudesic.MediAssistsPolicyService.exceptions.ResourceNotFoundException;
import com.neudesic.MediAssistsPolicyService.modules.Policy;
import com.neudesic.MediAssistsPolicyService.modules.UserPolicyDetails;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PolicyServiceWithEntityManager {

    @PersistenceContext
    private EntityManager entityManager;


    public void createPolicy(Policy policy){
        entityManager.persist(policy);
    }

    public Policy updatePolicy(Policy policy){
        return entityManager.merge(policy);
    }

    public Policy getPolicyById(Integer id){
        return entityManager.createQuery("select p from policy p where p.id=:id", Policy.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public Policy updatePolicy(Integer id, Policy policy){
        if(getPolicyById(id)!=null){
            return entityManager.merge(policy);
        }else {
            throw new ResourceNotFoundException("policy","Policy",id);
        }
    }

    public List<Policy> getAllPolicy(){
        return entityManager.createQuery("select p from policy p", Policy.class).getResultList();
    }

    public void deletePolicyById(Policy policy){
        entityManager.remove(policy);
    }

    public List<UserPolicyDetails> getUserPolicyDetails(Integer userId){
        TypedQuery<UserPolicyDetails> typedQuery = entityManager.createQuery("select d from UserPolicyDetails where d.userId = :userId", UserPolicyDetails.class);
        typedQuery.setParameter("userId", userId);
        return typedQuery.getResultList();
    }

    public List<Policy> getUsersPolicyByNative(Integer userId){
        TypedQuery<Policy> typedQuery = entityManager.createQuery("select p.* from policy p inner join userPolicyDetails u on u.id = p.id where u.userId = ?", Policy.class);
        typedQuery.setParameter(1, userId);
        return typedQuery.getResultList();
    }

    public List<Policy> getUsersPolicyByJpql(Integer userId){
        TypedQuery<Policy> typedQuery = entityManager.createQuery("select p from Policy p inner join p.userPolicyDetails u where u.userId = :userId", Policy.class);
        typedQuery.setParameter("userId", userId);
        return typedQuery.getResultList();
    }




}
