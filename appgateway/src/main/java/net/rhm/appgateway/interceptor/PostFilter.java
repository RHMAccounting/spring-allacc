package net.rhm.appgateway.interceptor;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import net.rhm.appgateway.utils.LogHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

public class PostFilter extends ZuulFilter {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private static final String ACCESS_TOKEN = "ACCESS_TOKEN";
    private static final String TOKEN_TYPE = "Bearer";

    private OAuth2RestOperations restTemplate;

    @Autowired
    private OAuth2ClientContext oAuth2ClientContext;

    public void setRestTemplate(OAuth2RestOperations restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        LOGGER.debug("\n\nDo We filter ?\n\n");


        if (auth instanceof OAuth2Authentication) {
            Object details = auth.getDetails();
            if (details instanceof OAuth2AuthenticationDetails) {
                OAuth2AuthenticationDetails oauth = (OAuth2AuthenticationDetails) details;
                RequestContext ctx = RequestContext.getCurrentContext();

                LOGGER.debug ("role " + auth.getAuthorities());

                LOGGER.debug("scope" + oAuth2ClientContext.getAccessToken().getScope());

                ctx.set(ACCESS_TOKEN, oauth.getTokenValue());
                ctx.set(TOKEN_TYPE, oauth.getTokenType()==null ? "Bearer" : oauth.getTokenType());
                return true;
            }
        }
        return false;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.addZuulRequestHeader("x-pp-user", ctx.get(TOKEN_TYPE) + " " + getAccessToken(ctx));

        ctx.getResponse().addHeader("Authorization","Bearer " + getAccessToken(ctx));
        LOGGER.debug("RESPONSE HEADERS : " + LogHeaders.displayHeaders(ctx.getResponse()));

        return null;
    }

    private String getAccessToken(RequestContext ctx) {
        String value = (String) ctx.get(ACCESS_TOKEN);
        if (restTemplate != null) {
            // In case it needs to be refreshed
            OAuth2Authentication auth = (OAuth2Authentication) SecurityContextHolder
                    .getContext().getAuthentication();
            if (restTemplate.getResource().getClientId()
                    .equals(auth.getOAuth2Request().getClientId())) {
                try {
                    value = restTemplate.getAccessToken().getValue();
                }
                catch (Exception e) {
                    // Quite possibly a UserRedirectRequiredException, but the caller
                    // probably doesn't know how to handle it, otherwise they wouldn't be
                    // using this filter, so we rethrow as an authentication exception
                    throw new BadCredentialsException("Cannot obtain valid access token");
                }
            }
        }
        return value;
    }
}
