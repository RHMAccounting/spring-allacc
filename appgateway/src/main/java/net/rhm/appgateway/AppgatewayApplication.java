package net.rhm.appgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableOAuth2Sso
@EnableEurekaClient
@EnableZuulProxy
public class AppgatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppgatewayApplication.class, args);
    }

}

