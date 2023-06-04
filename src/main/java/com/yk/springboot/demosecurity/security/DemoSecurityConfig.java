package com.yk.springboot.demosecurity.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class DemoSecurityConfig {

    /*
     * @Bean
     * public InMemoryUserDetailsManager userDetailsManager() {
     * UserDetails john = User.builder().username("john")
     * .roles("EMPLOYEE")
     * .password("{noop}test1234")
     * .build();
     * UserDetails mary = User.builder().username("mary")
     * .roles("EMPLOYEE", "MANAGER")
     * .password("{noop}test1234")
     * .build();
     * UserDetails susan = User.builder().username("susan")
     * .roles("EMPLOYEE", "MANAGER", "ADMIN")
     * .password("{noop}test1234")
     * .build();
     * 
     * return new InMemoryUserDetailsManager(john, mary, susan);
     * }
     */

    // add jdbc support
    @Bean
    public UserDetailsManager jdbDetailsManager(DataSource dataSource) {

        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(configurer -> configurer
                .requestMatchers("/").hasRole("EMPLOYEE")
                .requestMatchers("/leaders").hasAnyRole("MANAGER", "ADMIN")
                .requestMatchers("/admins").hasAnyRole("ADMIN")
                .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/showMyLoginPage")
                        .loginProcessingUrl("/authenticateTheUser")
                        .permitAll())
                .logout(logout -> logout.permitAll())
                .exceptionHandling(configurer -> configurer.accessDeniedPage("/access-denied"));
        return http.build();
    }

}
