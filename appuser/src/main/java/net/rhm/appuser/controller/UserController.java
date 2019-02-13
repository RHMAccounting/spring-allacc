package net.rhm.appuser.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;

@RestController
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/users")
    @SuppressWarnings("unchecked")
    public String howdy(Model model, Principal principal) {
        OAuth2Authentication authentication = (OAuth2Authentication) principal;
        Map<String, Object> user = (Map<String, Object>) authentication.getUserAuthentication().getDetails();
        model.addAttribute("user", user);

        LOGGER.debug("Authenticating user... " + user.toString());
        return "home";
    }
}
