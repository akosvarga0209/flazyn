package com.flazyn.controller;


import com.flazyn.dto.NatureDTO;
import com.flazyn.dto.UserObjectDTO;
import com.flazyn.dto.UserProfileDTO;
import com.flazyn.map.UserMapper;
import com.flazyn.service.NatureService;
import com.flazyn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class UserController {

    private UserService userService;
    private NatureService natureService;

    @Autowired
    public UserController(UserService userService,  NatureService natureService){
        this.userService = userService;
        this.natureService = natureService;
    }

    @GetMapping("/getUserObject")
    public UserObjectDTO whoami(Principal principal){
        return UserMapper.userToUserObject(userService.findByEmail(principal.getName()));
    }

    @PostMapping("/saveUserProfile")
    public ResponseEntity saveUserProfile(@RequestBody UserProfileDTO userProfileDTO){
        userService.saveUserProfile(userProfileDTO);
        return ResponseEntity.ok(HttpEntity.EMPTY);
    }

    @GetMapping("/getUserProfile")
    public ResponseEntity<UserProfileDTO> getUserProfile() {
        return ResponseEntity.ok(userService.getProfile());
    }

    @GetMapping("/getNatures")
    public ResponseEntity<Set<NatureDTO>> getNatures(){
        return ResponseEntity.ok(natureService.getNatures());
    }

}
