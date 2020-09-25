package com.epam.mentoring.springmvc.config.security;

import com.epam.mentoring.springmvc.passwordencoder.DisabledPasswordEncoder;
import com.epam.mentoring.springmvc.security.CommonAuthenticationSuccessHandler;
import com.epam.mentoring.springmvc.security.CommonLogoutSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Analogue of xml security configuration
 *
 * @author Kaikenov Adilhan
**/
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final String HOME_URL = "/home";

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new DisabledPasswordEncoder();
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/sign-up").anonymous()
                .antMatchers("/product/**").hasRole("ADMIN")
                .antMatchers("/profile/**").authenticated()
                .anyRequest().permitAll()
                .and()
                .formLogin().loginPage("/login")
                .defaultSuccessUrl(HOME_URL)
                .failureUrl("/login?error")
                .successHandler(authenticationSuccessHandler())
                .usernameParameter("username").passwordParameter("password")
                .and()
                .exceptionHandling().accessDeniedPage("/access-denied")
                .and()
                .logout()
                .logoutSuccessHandler(logoutSuccessHandler())
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout");
    }

    @Bean
    public CommonAuthenticationSuccessHandler authenticationSuccessHandler() {
        final CommonAuthenticationSuccessHandler authenticationSuccessHandler = new CommonAuthenticationSuccessHandler();
        authenticationSuccessHandler.setUrlForRedirection(HOME_URL);
        return authenticationSuccessHandler;
    }

    @Bean
    public CommonLogoutSuccessHandler logoutSuccessHandler() {
        final CommonLogoutSuccessHandler logoutSuccessHandler = new CommonLogoutSuccessHandler();
        logoutSuccessHandler.setUrlForRedirection(HOME_URL);
        return logoutSuccessHandler;
    }
}
