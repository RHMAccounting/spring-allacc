package net.rhm.appuser.factory.oauth;

import net.rhm.appuser.model.entity.User;

import java.util.Map;

public class Okta {

    public static User mapUser(User user, Map<String, Object> principalDetails) {

        user.setFirstName((String)principalDetails.get("given_name"));
        user.setLastName((String)principalDetails.get("family_name"));
        user.setEmail((String)principalDetails.get("email"));
        user.setUserName((String)principalDetails.get("preferred_username"));

        return user;
    }
}
