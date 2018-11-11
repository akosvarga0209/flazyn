package com.flazyn.controller;

import com.flazyn.dto.GeneralMessageDTO;
import com.flazyn.entities.Role;
import com.flazyn.entities.User;
import com.flazyn.exception.EmailAlreadyExistsException;
import com.flazyn.exception.GeneralServerErrorException;
import com.flazyn.repository.UserRepository;
import com.flazyn.service.UserService;
import com.flazyn.dto.UserRegisterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Collection;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class HomeController {

    UserService userService;

    @Value("${clientId}")
    private  String CLIENT_ID;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenStore tokenStore;

    @Autowired
    @Qualifier(value = "myDefault")
    DefaultTokenServices defaultTokenServices;

    @Autowired
    HomeController(UserService userService) {
        this.userService = userService;

    }

    @PostMapping("/register")
    public ResponseEntity<GeneralMessageDTO> registerUser(@RequestBody UserRegisterDTO userToRegisterDTO) throws EmailAlreadyExistsException, GeneralServerErrorException {
        return ResponseEntity.ok(userService.registrationChecks(userToRegisterDTO));
    }

    @GetMapping("/activation/")
    public ResponseEntity activateUser(String code) throws Exception {
        return ResponseEntity.ok(userService.userActivation(code));
    }

    @GetMapping("/resetActivation/")
    public ResponseEntity resetActivateUser(String email) throws Exception {
        return ResponseEntity.ok(userService.resetUserActivation(email));
    }

    @GetMapping("/fbLogin")
    public OAuth2AccessToken token(@RequestParam String fbToken) {
        return userService.loginWithFacebook(fbToken);
    }

    @RequestMapping("/logout")
    public ResponseEntity logout(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null) {
            String tokenValue = authHeader.replace("Bearer", "").trim();
            OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
            defaultTokenServices.revokeToken(accessToken.getValue());
        }
        return ResponseEntity.ok("Successfull logout");
    }
}
