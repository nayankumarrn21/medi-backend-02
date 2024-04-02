package com.neudesic.MediAssistsPolicyService.repositories;

import com.neudesic.MediAssistsPolicyService.modules.Policy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PolicyRepositoryLearn extends JpaRepository<Policy, Integer> {

    @Query("select p from Policy p where p.insuredAmount > :insuredAmount ")
    List<Policy> getAllPolicyByGreaterAmount(@Param("insuredAmount") String insuredAmount);

    @Query("select p from Policy p where p.insuredAmount > ?1")
    List<Policy> getAllPolicyByGreaterAmount1(String insuredAmount);
}
