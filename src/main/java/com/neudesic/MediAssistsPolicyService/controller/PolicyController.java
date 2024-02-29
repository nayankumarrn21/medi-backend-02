package com.neudesic.MediAssistsPolicyService.controller;


import com.neudesic.MediAssistsPolicyService.exceptions.BindingResultException;
import com.neudesic.MediAssistsPolicyService.modules.Policy;
import com.neudesic.MediAssistsPolicyService.services.PolicyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/policy")
public class PolicyController {

    @Autowired
    private PolicyService policyService;

    @PostMapping
    public ResponseEntity<Policy> createPolicy(@Valid @RequestBody Policy policy, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new BindingResultException("Policy Field errors", bindingResult);
        }
        return ResponseEntity.ok(policyService.createPolicy(policy));
    }

    @GetMapping("list")
    public ResponseEntity<List<Policy>> getAll(){
        return ResponseEntity.ok(policyService.getAll());
    }

    @GetMapping("{pId}")
    public ResponseEntity<Policy> getPolicy(@PathVariable("pId") Integer pid){
        return ResponseEntity.ok(policyService.getPolicy(pid));
    }
    @DeleteMapping("{pId}")
    public ResponseEntity<String> deletePolicy(@PathVariable("pId") Integer pid){
        return ResponseEntity.ok(policyService.deletePolicy(pid));
    }

    @GetMapping("test")
    public ResponseEntity<String> test(){
        return ResponseEntity.ok("Testing");
    }
}
