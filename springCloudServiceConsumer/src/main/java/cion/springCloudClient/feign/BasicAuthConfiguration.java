package cion.springCloudClient.feign;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.auth.BasicAuthRequestInterceptor;

@Configuration
public class BasicAuthConfiguration {
 
	@Value("${spring.security.user.name}")
	private String username;
	@Value("${spring.security.user.password}")
	private String password;
	
	
    @Bean
    public BasicAuthRequestInterceptor basicAuthorizationInterceptor() {
        return new BasicAuthRequestInterceptor(username, password);
    }
}