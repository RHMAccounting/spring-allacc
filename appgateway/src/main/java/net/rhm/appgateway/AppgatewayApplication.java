package net.rhm.appgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableOAuth2Sso
@EnableEurekaClient
@EnableZuulProxy
@EnableSwagger2
public class AppgatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppgatewayApplication.class, args);
    }

}

