package net.rhm.appgateway.config;

import net.rhm.appgateway.interceptor.CsrfHeaderFilter;
import net.rhm.appgateway.interceptor.PostFilter;
import net.rhm.appgateway.interceptor.QueryParamPreFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.util.matcher.RequestHeaderRequestMatcher;


@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .and()
                .requestMatcher(new RequestHeaderRequestMatcher("Authorization"))
                .authorizeRequests()
                .antMatchers("/**").authenticated()
                .and()
                .addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class)
         ;
    }


    @Bean
    public QueryParamPreFilter queryParamPreFilter(){
        return new QueryParamPreFilter();
    }

    @Bean
    public PostFilter postFilter(){
        return new PostFilter();
    }
}