package net.rhm.appuser.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Retrieving data from different OAuth2 servers
 */
@Component
public class PrincipalFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(PrincipalFactory.class);

    /**
     * Given principal details as a map
     * @param principalDetails represent user details fetched from OAuth2 server
     * @return a string with the ID depending on the server
     */
    public String getPrincipalId(Map<String, Object> principalDetails) {

        LOGGER.debug("Map of principal " + principalDetails);

        return "";
    }
}
