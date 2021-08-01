package com.wat.reservation_cinema_system_backend.config;


import com.wat.reservation_cinema_system_backend.user.UserFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



@Configuration
public class SpringConfiguration {


    @Bean
    public UserFactory userFactory() {
        return new UserFactory(new BCryptPasswordEncoder());
    }


}
