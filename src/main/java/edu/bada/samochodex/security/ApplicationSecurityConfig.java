package edu.bada.samochodex.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static edu.bada.samochodex.security.ApplicationUserPermission.*;
import static edu.bada.samochodex.security.ApplicationUserRole.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails mateuszUser = User.builder()
                .username("mateusz")
                .password(passwordEncoder.encode("admin"))
                .authorities(ADMIN.getGrantedAuthorities())
                .build();

        UserDetails kacperUser = User.builder()
                .username("kacper")
                .password(passwordEncoder.encode("admin"))
                .authorities(ADMIN.getGrantedAuthorities())
                .build();

        UserDetails zbyszekUser = User.builder()
                .username("zbyszek")
                .password(passwordEncoder.encode("123"))
                .authorities(USER.getGrantedAuthorities())
                .build();

        UserDetails monikaUser = User.builder()
                .username("monika")
                .password(passwordEncoder.encode("12345"))
                .authorities(MODERATOR.getGrantedAuthorities())
                .build();

        return new InMemoryUserDetailsManager(
                mateuszUser,
                kacperUser,
                zbyszekUser,
                monikaUser
        );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // TODO: Remove line below as soon as csrf token can be passed to server via requests
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js/*").permitAll()
                .antMatchers("/poczty/**").hasAnyRole(ADMIN.name(), MODERATOR.name())
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll();
                // TODO: Uncomment this and comment "permitAll()" above to set custom login page
                //.loginPage("/login").permitAll();
    }
}
