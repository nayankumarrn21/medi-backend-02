package com.neudesic.MediAssistsPolicyService.controller;


import com.neudesic.MediAssistsPolicyService.dto.UserPolicyDto;
import com.neudesic.MediAssistsPolicyService.modules.UserPolicyDetails;
import com.neudesic.MediAssistsPolicyService.services.UserPolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-policy")
public class UserPolicyController {

    @Autowired
    private UserPolicyService userPolicyService;

    @PostMapping
    public ResponseEntity<UserPolicyDetails> create(@RequestBody UserPolicyDto userPolicyDto){
        return ResponseEntity.ok(userPolicyService.create(userPolicyDto));
    }

    @GetMapping("/user/{uId}")
    public ResponseEntity<List<UserPolicyDetails>> getListOfUserDetails(@PathVariable("uId") Integer uId ){
        return ResponseEntity.ok(userPolicyService.getListOfUserDetails(uId));
    }
}
