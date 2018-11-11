package com.flazyn.service;

import com.flazyn.dto.GeneralMessageDTO;
import com.flazyn.dto.UserObjectDTO;
import com.flazyn.dto.UserProfileDTO;
import com.flazyn.dto.UserRegisterDTO;
import com.flazyn.entities.User;
import com.flazyn.exception.EmailAlreadyExistsException;
import com.flazyn.exception.GeneralServerErrorException;
import com.flazyn.exception.UserAcivationException;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

import java.util.List;

public interface UserService {

    User save(User user);
    List<User> findAll();
    void delete(long id);
    User findByUsername(String name);
    User findByActivation(String activation);
    User findByEmail(String email);
    GeneralMessageDTO registrationChecks(UserRegisterDTO user) throws EmailAlreadyExistsException, GeneralServerErrorException;
    boolean registerUser(UserRegisterDTO user);
    UserObjectDTO userActivation(String code) throws UserAcivationException;
    UserObjectDTO resetUserActivation(String code) throws UserAcivationException;
    OAuth2AccessToken loginWithFacebook(String fbToken);
    void saveUserProfile(UserProfileDTO userProfileDTO);
    UserProfileDTO getProfile();
}