package net.rhm.appuser.controller;

import net.rhm.appuser.factory.PrincipalFactory;
import net.rhm.appuser.model.entity.AuthServer;
import net.rhm.appuser.model.entity.AuthServerUser;
import net.rhm.appuser.model.entity.User;
import net.rhm.appuser.model.repository.AuthServerUserRepository;
import net.rhm.appuser.model.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;

/**
 * Everything related to signing assuming we have a Principal object
 * from an existing token sent from a third part OAuth2 service such as Okta or Keycloak...
 */
@RestController
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    private AuthServerUserRepository authUserServerRepository;
    private PrincipalFactory principalFactory;
    private UserRepository userRepository;

    @Autowired
    public LoginController(PrincipalFactory principalFactory,
                           AuthServerUserRepository authUserServerRepository,
                           UserRepository userRepository) {
        this.principalFactory = principalFactory;
        this.userRepository = userRepository;
        this.authUserServerRepository = authUserServerRepository;
    }
    /**
     * Endpoint checking whether principal already exists in database, otherwise create it
     * @param principal authenticated user using Token
     * @return user
     */
    @GetMapping("/signin")
    @ResponseBody
    @SuppressWarnings({"unchecked"})
    public User signIn(Principal principal) {

        OAuth2Authentication authentication = (OAuth2Authentication) principal;
        Map<String, Object> details = (Map<String, Object>) authentication.getUserAuthentication().getDetails();

        try {
            this.principalFactory.setPrincipalDetails(details);
            AuthServerUser authSrvUser = authUserServerRepository.findByUserId(this.principalFactory.getPrincipalId());

            if(authSrvUser != null)
                return authSrvUser.getAuthUser().getUser();
            else {
                // Register as new user here :
                User newUser = this.principalFactory.mapUser();

                return newUser;
            }
        }
        catch(RuntimeException e) {
            LOGGER.error("Error fetching user..." + e.getMessage());
        }

        return null;

    }

}
