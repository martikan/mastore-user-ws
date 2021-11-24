package org.martikan.mastore.userapi.security;

import lombok.RequiredArgsConstructor;
import org.martikan.mastore.userapi.service.UserService;
import org.martikan.mastore.userapi.utils.JwtUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    private final UserService userService;

    private final BCryptPasswordEncoder passwordEncoder;

    private final JwtUtils jwtUtils;

//    @Value("${gateway.ip}")
//    private String gatewayIp;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/**")
                .permitAll()
//                .hasIpAddress(gatewayIp)
                .and()
                .addFilter(getAuthenticationFilter());
        http.headers().frameOptions().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }

    private AuthenticationFilter getAuthenticationFilter() throws Exception {

        final var authenticationFilter = new AuthenticationFilter(userService, jwtUtils);
        authenticationFilter.setFilterProcessesUrl("/signIn");
        authenticationFilter.setAuthenticationManager(authenticationManager());

        return authenticationFilter;
    }

}
