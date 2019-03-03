package net.rhm.appuser.controller;

import net.rhm.appuser.model.entity.User;
import net.rhm.appuser.model.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Map;

/**
 * User Controller responding to CRUD requests related to user management.
 */
@RestController
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;
    /**
     * Method returning all users depending on role (ROLE_MASTER has access to all without limitation)
     * @param principal authenticated user
     * @return user details as JSON
     */
    @GetMapping("/users")
    @ResponseBody
    @SuppressWarnings("unchecked")
    public Object listUsers(Principal principal, @RequestHeader HttpHeaders headers) {

        OAuth2Authentication authentication = (OAuth2Authentication) principal;
        Map<String, Object> details = (Map<String, Object>) authentication.getUserAuthentication().getDetails();

        LOGGER.debug("Authenticated user... " + details.toString());
        LOGGER.debug("Authenticated user email " + details.get("email").toString());
        LOGGER.debug("HEADERS : {}", headers);

        return details;
    }


    @PutMapping("/user")
    public ResponseEntity<User> updateUser(@Valid @RequestBody User user, @RequestHeader HttpHeaders headers) {

        LOGGER.debug("Request to update user : {}", user);
        LOGGER.debug("HEADERS : {}", headers);
        userRepository.save(user);
        return ResponseEntity.ok().body(user);
    }


}
