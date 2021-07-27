package com.example.soft.configutation;

import com.example.soft.security.jwt.JwtConfigurer;
import com.example.soft.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import javax.sql.DataSource;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;
    private final DataSource dataSource;

    private static final String ADMIN_ENDPOINT_EMPLOYEES = "/users";
    private static final String ADMIN_ENDPOINT_EMPLOYEE = "/users/**";
    private static final String STUFF_ENDPOINT_ALL = "/**";
    private static final String LOGIN_ENDPOINT = "/auth/login";


    @Autowired
    public SecurityConfig(JwtTokenProvider jwtTokenProvider, DataSource dataSource) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.dataSource = dataSource;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v3/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(LOGIN_ENDPOINT).permitAll()
                .antMatchers(ADMIN_ENDPOINT_EMPLOYEES).hasRole("ADMIN")
                .antMatchers(ADMIN_ENDPOINT_EMPLOYEE).hasRole("ADMIN")
                .antMatchers(STUFF_ENDPOINT_ALL).hasAnyRole("SALES","ADMIN")
                .anyRequest().authenticated()
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider));
    }
}