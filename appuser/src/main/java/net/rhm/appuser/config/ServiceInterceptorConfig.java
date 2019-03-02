package net.rhm.appuser.config;

import net.rhm.appuser.interceptor.ServiceInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class ServiceInterceptorConfig implements WebMvcConfigurer {

    @Autowired
    ServiceInterceptor ServiceInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(ServiceInterceptor);
    }
}
