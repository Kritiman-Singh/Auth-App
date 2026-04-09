package com.auth.config;


import com.auth.entities.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsService users(){
        User.UserBuilder userBuilder = User.withDefaultPasswordEncoder();
      UserDetails user1 =  userBuilder.username("raj").password("123").roles("ADMIN").build();
    }

}
