package com.example.users.Config;

import com.example.users.Service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ConfigSecurity {

    private final MyUserDetailsService myUserDetailsService;
@Bean  //make any methods works fine
    public DaoAuthenticationProvider daoAuthenticationProvider(){

DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
daoAuthenticationProvider.setUserDetailsService(myUserDetailsService);
daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
return daoAuthenticationProvider;
}

@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf().disable()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
            .and()
            .authenticationProvider(daoAuthenticationProvider())
            .authorizeHttpRequests()
            .requestMatchers("/api/v1/user/add").permitAll()
            .requestMatchers("/api/v1/blog/get-my").hasAuthority("USER")
            .requestMatchers("/api/v1/blog/add").hasAuthority("USER")
            .requestMatchers("/api/v1/user/get").hasAuthority("ADMIN")
            .requestMatchers("/api/v1/blog/update/{id2}").hasAuthority("USER")
            .requestMatchers("/api/v1/blog/delete/{id}").hasAuthority("USER")
            .requestMatchers("/api/v1/blog/get/id/{id}").hasAuthority("USER")
            .requestMatchers("/api/v1/blog/get/title/{title}").hasAuthority("USER")
            .requestMatchers("/api/v1/blog/get-all").hasAuthority("ADMIN")
            .requestMatchers("/api/v1/user/update/{id}").hasAuthority("USER")
            .requestMatchers("/api/v1/user/delete/{id}").hasAuthority("ADMIN")
            .anyRequest().authenticated()
            .and().logout().logoutUrl("/api/v1/user/logout").deleteCookies("JSESSIONID")
            .invalidateHttpSession(true).and().httpBasic();

    return http.build();

}

}
