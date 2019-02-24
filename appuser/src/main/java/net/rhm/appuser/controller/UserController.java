package net.rhm.appuser.controller;

import net.rhm.appuser.model.entity.User;
import net.rhm.appuser.model.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private UserRepository userRepository;
    /**
     * Method returning all users depending on role (ROLE_MASTER has access to all without limitation)
     * @param principal authenticated user
     * @return user details as JSON
     */
    @GetMapping("/users")
    @ResponseBody
    @SuppressWarnings("unchecked")
    public Object listUsers(Principal principal) {

        OAuth2Authentication authentication = (OAuth2Authentication) principal;
        Map<String, Object> details = (Map<String, Object>) authentication.getUserAuthentication().getDetails();

        LOGGER.debug("Authenticated user... " + details.toString());
        LOGGER.debug("Authenticated user email " + details.get("email").toString());

        return details;
    }


    @PutMapping("/user")
    public ResponseEntity<User> updateUser(@Valid @RequestBody User user) {

        LOGGER.debug("Request to update user : {}", user);
        User result = userRepository.save(user);
        return ResponseEntity.ok().body(result);
    }


}
