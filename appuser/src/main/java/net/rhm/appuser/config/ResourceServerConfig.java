package net.rhm.appuser.config;

import net.rhm.appuser.filter.CustomFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.util.matcher.RequestHeaderRequestMatcher;

/**
 *  a component that requires an access token to allow, or at least consider, access to its resources
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .requestMatcher(new RequestHeaderRequestMatcher("Authorization"))
                .authorizeRequests().anyRequest().fullyAuthenticated();
    }

    @Bean
    public FilterRegistrationBean <CustomFilter> filterRegistrationBean() {
        FilterRegistrationBean < CustomFilter > registrationBean = new FilterRegistrationBean();
        CustomFilter customFilter = new CustomFilter();

        registrationBean.setFilter(customFilter);
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(2); //set precedence
        return registrationBean;
    }
}