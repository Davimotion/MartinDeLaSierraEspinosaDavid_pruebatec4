package com.example.travelAgencyApi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity

                //disables a protection that limits testing with your own server. Pretty recent stuff according to the teacher.
                .csrf().disable()

                .authorizeHttpRequests()
                .requestMatchers("/agency", "/agency/flights" ,"/agency/flights/filtered",
                        "/agency/hotels", "/agency/hotels/get_available", "/agency/flight-booking/new",
                        "/agency/rooms/from_hotel/{hotelId}", "agency/reservations/new" , "/doc/swagger-ui.html").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll()
                .and()
                .httpBasic()
                .and()
                .build();
    }
}
