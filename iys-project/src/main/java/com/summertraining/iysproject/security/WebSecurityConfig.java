package com.summertraining.iysproject.security;

import com.summertraining.iysproject.jwt.JwtTokenFilter;
import com.summertraining.iysproject.repo.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true
)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired private PersonRepository personRepository;
    @Autowired private JwtTokenFilter jwtTokenFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(username -> personRepository.findByUsername(username));
    }

    /**
     * Basic configuration of our security
     * For instance, we are disabling cookies and no session management
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors(); // adding cors policy to our security filter

        http.csrf().disable(); // disable cookies

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // no sessions

        // make sure to return 401 in case of error
        http.exceptionHandling().authenticationEntryPoint(
                ((request, response, e) -> {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                    e.getMessage();
                })
        );

        http.authorizeRequests()
                .antMatchers("/api/login").permitAll()

                .antMatchers("/methods/21").access("hasAuthority('ADMIN')")
                .antMatchers("/methods/22").access("hasAuthority('ADMIN')")

                .anyRequest().authenticated();

//        .anyRequest().permitAll();


        // make sure to add our Username and Password security
        // before UsernamePasswordAuth
        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
