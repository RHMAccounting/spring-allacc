package net.rhm.appuser.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;

/**
 * User Controller responding to CRUD requests related to user management.
 */
@RestController
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

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


}
