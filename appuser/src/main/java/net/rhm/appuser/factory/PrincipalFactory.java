package net.rhm.appuser.factory;

import net.rhm.appuser.factory.oauth.Okta;
import net.rhm.appuser.model.entity.AuthServer;
import net.rhm.appuser.model.entity.User;
import net.rhm.appuser.model.repository.AuthServerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

/**
 * Retrieving data from different OAuth2 servers
 */
@Service
public class PrincipalFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(PrincipalFactory.class);

    private Map<String, Object> principalDetails;
    private AuthServerRepository authServerRepository;
    private AuthServer authServer;

    @Autowired
    public PrincipalFactory(AuthServerRepository authServerRepository){

        this.authServerRepository = authServerRepository;
    }
    /**
     * Setter
     * @param principalDetails represent user details fetched from OAuth2 server
     */
    public void setPrincipalDetails(Map<String, Object> principalDetails) {
        this.principalDetails = principalDetails;
    }

    /**
     * Given principal details as a map
     * @return a string with the ID depending on the server
     */
    public String getPrincipalId() {

        LOGGER.debug("Map of principal " + this.principalDetails);

        // Case Okta :
        if (this.principalDetails.containsKey("sub")) {

            this.authServer = this.authServerRepository.findByName("Okta");

            return (String)this.principalDetails.get("sub");
        }

        return null;
    }

    /**
     * Mapping principal to user depending on server
     * @return a user instance
     */
    public Map<User,AuthServer> mapUser() {

        User newUser = new User();

        switch(this.authServer.getName()) {
            case "Okta":
                return Collections.singletonMap(Okta.mapUser(newUser, this.principalDetails), this.authServer);

            default:
                LOGGER.error("Error matching server !");
                throw new RuntimeException();
        }
    }

    public AuthServer getServer() {
        return this.authServer;
    }
}
