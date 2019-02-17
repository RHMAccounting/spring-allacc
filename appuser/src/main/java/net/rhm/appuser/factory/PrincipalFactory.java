package net.rhm.appuser.factory;

import net.rhm.appuser.factory.oauth.Okta;
import net.rhm.appuser.model.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Retrieving data from different OAuth2 servers
 */
@Service
public class PrincipalFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(PrincipalFactory.class);

    private String server;
    private Map<String, Object> principalDetails;

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
            this.server = "Okta";
            return (String)this.principalDetails.get("sub");
        }

        return null;
    }

    /**
     * Mapping principal to user depending on server
     * @return a user instance
     */
    public User mapUser() {

        User newUser = new User();

        switch(this.server) {
            case "Okta":
                return Okta.mapUser(newUser, this.principalDetails);

            default:
                LOGGER.error("Error matching server !");
                throw new RuntimeException();
        }
    }

    public String getServer() {
        return this.server;
    }
}
