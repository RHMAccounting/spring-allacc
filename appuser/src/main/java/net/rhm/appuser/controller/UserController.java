package net.rhm.appuser.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;

@RestController
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/users")
    @ResponseBody
    @SuppressWarnings("unchecked")
    public Object howdy(Principal principal) {

        OAuth2Authentication authentication = (OAuth2Authentication) principal;
        Map<String, Object> details = (Map<String, Object>) authentication.getUserAuthentication().getDetails();

        LOGGER.debug("Authenticated user... " + details.toString());
        LOGGER.debug("Authenticated user email " + details.get("email").toString());

        return details;
    }
}
