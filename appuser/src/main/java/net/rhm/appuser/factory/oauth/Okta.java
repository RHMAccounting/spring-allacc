package net.rhm.appuser.factory.oauth;

import net.rhm.appuser.model.embeddable.AuthUser;
import net.rhm.appuser.model.entity.AuthServer;
import net.rhm.appuser.model.entity.AuthServerUser;
import net.rhm.appuser.model.entity.User;
import net.rhm.appuser.model.repository.AuthServerUserRepository;
import net.rhm.appuser.model.repository.UserRepository;

import java.util.Map;

public class Okta extends OAuthServer {

    public static User mapUserAndSave(User user, Map<String, Object> principalDetails, AuthServer authServer,
                                      AuthServerUserRepository authServerUserRepository, UserRepository userRepository) {

        user.setFirstName((String)principalDetails.get("given_name"));
        user.setLastName((String)principalDetails.get("family_name"));
        user.setEmail((String)principalDetails.get("email"));
        user.setUserName((String)principalDetails.get("preferred_username"));

        LOGGER.debug("Persisting user here...");
        userRepository.save(user);
        LOGGER.debug("Id generated: " + user.getId());

        LOGGER.debug("Now about to persist AuthServerUser");

        // Generate authUser object
        AuthUser authUser = new AuthUser();
        authUser.setAuthServer(authServer);
        authUser.setUser(user);

        AuthServerUser authServerUser = new AuthServerUser();
        authServerUser.setAuthUser(authUser);
        authServerUser.setUserId((String)principalDetails.get("sub")); // specific to Okta

        authServerUserRepository.save(authServerUser);

        return user;
    }
}
