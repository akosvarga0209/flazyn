package com.flazyn.service;


import com.flazyn.config.oauth.CustomUserDetails;
import com.flazyn.config.oauth.LoginAttemptService;
import com.flazyn.dto.GeneralMessageDTO;
import com.flazyn.dto.UserObjectDTO;
import com.flazyn.dto.UserProfileDTO;
import com.flazyn.dto.UserRegisterDTO;
import com.flazyn.entities.Role;
import com.flazyn.entities.User;
import com.flazyn.exception.EmailAlreadyExistsException;
import com.flazyn.exception.GeneralServerErrorException;
import com.flazyn.exception.UserAcivationException;
import com.flazyn.map.UserMapper;
import com.flazyn.repository.RoleRepository;
import com.flazyn.repository.UserRepository;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.json.JsonObject;
import com.restfb.json.JsonValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private EmailService emailService;
    private NatureService natureService;

    @Value("${clientId}")
    private  String CLIENT_ID;

    @Autowired
    private LoginAttemptService loginAttemptService;

    @Autowired
    private BCryptPasswordEncoder bcEncoder;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private MessageSource messageSource;


    @Autowired
    @Qualifier(value = "myDefault")
    DefaultTokenServices defaultTokenServices;

    private final String USER_ROLE = "ROLE_USER";
    private static final String ACTIVATION_KEY_EXPIRED_MSG = "activation_key_expired";
    private static final String INVALID_ACTIVATION_KEY_MSG = "activation_key_invalid";
    private static final String USER_ALREADY_ACTIVATED_MSG = "user_already_activated";
    private static final String INVALID_EMAIL_MSG = "invalid_email";

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           EmailService emailService,
                           NatureService natureService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.emailService = emailService;
        this.natureService = natureService;
    }

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        //check ip blocked or not
        String ip = getClientIP();
        if (loginAttemptService.isBlocked(ip)) {
            throw new RuntimeException("blocked");
        }

        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid email or password.");
        }
        CustomUserDetails customUserDetails = new CustomUserDetails(user);
        return new org.springframework.security.core.userdetails.User(customUserDetails.getUsername(), customUserDetails.getPassword(), customUserDetails.isEnabled(),
                customUserDetails.isAccountNonExpired(),customUserDetails.isCredentialsNonExpired(), customUserDetails.isAccountNonLocked(),customUserDetails.getAuthorities());

    }

    private List getAuthority() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    @Override
    public User save(User user) {
       return userRepository.save(user);
    }

    public List findAll() {
        throw new UsernameNotFoundException("Nincs kész");
    }

    @Override
    public void delete(long id) {
        throw new UsernameNotFoundException("Nincs kész");
    }

    @Override
    public User findByUsername(String name) {
        throw new UsernameNotFoundException("Nincs kész");
    }

    @Override
    public User findByActivation(String activation) {
        return null;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public GeneralMessageDTO registrationChecks(UserRegisterDTO user) throws EmailAlreadyExistsException, GeneralServerErrorException {
        User userCheck = findByEmail(user.getEmail());

        if (userCheck == null) {

            if (registerUser(user)) {
                return new GeneralMessageDTO("Successful registration!");
            }
            throw new GeneralServerErrorException();

        } //Already Registred
        throw new EmailAlreadyExistsException("User already registred with the given email address!");
    }

    @Override
    public boolean registerUser(UserRegisterDTO user) {
        User userToRegister = new User();
        userToRegister.setEmail(user.getEmail());
        userToRegister.setFullName(user.getFullName());
        userToRegister.setPassword(bcEncoder.encode(user.getPassword()));
        userToRegister.setActivation(generateKey());
        userToRegister.setEnabled(false);
        Role userRole = roleRepository.findByName(USER_ROLE);

        if (userRole != null) {
            userToRegister.getRoles().add(userRole);
        } else userToRegister.addRoles(USER_ROLE);

        userRepository.save(userToRegister);
        emailService.sendMessage(userToRegister.getEmail(), userToRegister.getActivation());
        return true;
    }

    @Override
    public UserObjectDTO userActivation(String code) throws UserAcivationException {
        Optional<User> userToActivate = userRepository.findByActivation(code);
        User user = userToActivate
                .orElseThrow(() -> new UserAcivationException(getMessage(INVALID_ACTIVATION_KEY_MSG, null, Locale.getDefault())));

        Timestamp registerTime = Timestamp.valueOf(user.getCreateDateTime());
        Timestamp currentTime = new Timestamp(new Date().getTime());
        //1 day old activation
        if ((currentTime.getTime() - registerTime.getTime()) > 86400000) {
            user.setCreateDateTime(LocalDateTime.now());
            throw new UserAcivationException(getMessage(ACTIVATION_KEY_EXPIRED_MSG, null, Locale.getDefault()));
        }
        activateUser(user);
        return new UserObjectDTO(user);
    }

    @Override
    public UserObjectDTO resetUserActivation(String email) throws UserAcivationException {
        User user = userRepository.findByEmail(email);
        //user with this email exist
        if (user != null) {
            //not yet activated
            if (!user.getEnabled()) {
                user.setActivation(generateKey());
                emailService.sendMessage(user.getEmail(), user.getActivation());
                return new UserObjectDTO(user);
            }
            throw new UserAcivationException(getMessage(USER_ALREADY_ACTIVATED_MSG, null, Locale.getDefault()));
        }
        throw new UserAcivationException(getMessage(INVALID_EMAIL_MSG, null, Locale.getDefault()));

    }

    @Override
    public void saveUserProfile(UserProfileDTO userProfileDTO) {
        User user = UserMapper.profileToUser(
                userProfileDTO,
                getUser(SecurityContextHolder.getContext().getAuthentication()));

        user.setNatures(natureService.findByIds(userProfileDTO.getNature()));
        userRepository.save(user);
    }

    @Override
    public UserProfileDTO getProfile() {
        return UserMapper.userToProfile(getUser(SecurityContextHolder.getContext().getAuthentication()));
    }

    private User activateUser(User userToActivate) throws UserAcivationException {
        userToActivate.setEnabled(true);
        userToActivate.setActivation("");
        userRepository.save(userToActivate);
        return userToActivate;
    }

    private String getMessage(String key, Object[] params, Locale locale) {
        return messageSource.getMessage(key, params, locale);
    }

    public String generateKey() {
        String key = "";
        Random random = new Random();
        char[] word = new char[16];

        for (int j = 0; j < word.length; j++) {
            word[j] = (char) ('a' + random.nextInt(26));
        }
        return new String(word);
    }

    private String getClientIP() {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null) {
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }

    public User getUser(Principal principal){
        return findByEmail(principal.getName());
    }

    public OAuth2AccessToken loginWithFacebook(String fbToken) {
        //get facebook id
        FacebookClient client = new DefaultFacebookClient(fbToken, Version.LATEST);
        JsonObject userJson = client.fetchObject("/me",JsonObject.class, Parameter.with("fields","id,name,email"));
        String fbId = userJson.get("id").toString();


        User userToLogin=userRepository.findByFbId(fbId);
        //Register user
        if (userToLogin==null){
            User userToSave = new User();
            userToSave.setFbId(fbId);
            userToSave.setPassword(bcEncoder.encode(generateKey()));
            userToSave.setEmail(userJson.getString("email",""));
            userToSave.setFullName(userJson.getString("name",""));
            JsonObject js =
                    client.fetchObject("/me/picture", JsonObject.class,
                            Parameter.with("type","large"),
                            Parameter.with("redirect","false"));
            JsonValue jsonValue = js.get("data");
            JsonObject object = jsonValue.asObject();
            String profileImageUrl = object.get("url").asString();
            userToSave.setProfilePicture(profileImageUrl);
            Role userRole = roleRepository.findByName(USER_ROLE);

            if (userRole != null) {
                userToSave.getRoles().add(userRole);
            } else userToSave.addRoles(USER_ROLE);
            userToLogin = userRepository.save(userToSave);
        }

        //Login
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        Map<String, String> requestParameters = new HashMap<>();
        String clientId = CLIENT_ID;
        boolean approved = true;
        Set<String> scope = new HashSet<>();
        scope.add("read write");
        Set<String> resourceIds = new HashSet<>();
        Set<String> responseTypes = new HashSet<>();
        responseTypes.add("code");
        Map<String, Serializable> extensionProperties = new HashMap<>();

        OAuth2Request oAuth2Request = new OAuth2Request(requestParameters, clientId,
                authorities, approved, scope,
                resourceIds, null, responseTypes, extensionProperties);

        org.springframework.security.core.userdetails.User userPrincipal = new org.springframework.security.core.userdetails.User(userToLogin.getEmail(), userToLogin.getPassword(), true, true, true, true, authorities);

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userPrincipal, null, authorities);
        OAuth2Authentication auth = new OAuth2Authentication(oAuth2Request, authenticationToken);
        OAuth2AccessToken token = defaultTokenServices.createAccessToken(auth);
        return token;
    }

}
