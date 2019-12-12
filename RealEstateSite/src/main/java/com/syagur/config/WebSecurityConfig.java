package com.syagur.config;

import com.syagur.service.impl.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        UserDetailsService userDetailsService = getUserDetails();
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable()
                .csrf().disable().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
                .antMatchers("/login").permitAll().antMatchers("/register").permitAll()
                .antMatchers(HttpMethod.GET, "/realties/").hasAnyAuthority("ADMIN", "USER")
                .antMatchers(HttpMethod.POST, "/realties/").hasAuthority("USER")
                .antMatchers(HttpMethod.DELETE, "/realties/{id}").hasAuthority("USER")
                .antMatchers(HttpMethod.PATCH, "/realties/{id}").hasAuthority("USER")
                .antMatchers(HttpMethod.GET, "/realties/{id}").hasAnyAuthority("ADMIN", "USER")
                .antMatchers("/realties/my").hasAuthority("USER")
                .antMatchers("/realties/deleted").hasAuthority("ADMIN")
                .antMatchers("/realties/deleted/{id}").hasAuthority("ADMIN")
                .antMatchers("/users/**").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and().csrf()
                .disable().exceptionHandling()
                .authenticationEntryPoint(unauthorizedEntryPoint()).and()
                .apply(new JwtConfig(jwtTokenProvider));
        http.cors();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
    }

    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public AuthenticationEntryPoint unauthorizedEntryPoint() {
        return (request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                "Unauthorized");
    }

    @Bean
    public UserDetailsService getUserDetails() {
        return new UserDetailsService();
    }
}


