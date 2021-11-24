package org.martikan.mastore.userapi.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.martikan.mastore.userapi.dto.user.UserSignInDTO;
import org.martikan.mastore.userapi.exception.ApiException;
import org.martikan.mastore.userapi.service.UserService;
import org.martikan.mastore.userapi.utils.JwtUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@RequiredArgsConstructor
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final UserService userService;

    private final JwtUtils jwtUtils;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        try {

            final var credentials = new ObjectMapper()
                    .readValue(request.getInputStream(), UserSignInDTO.class);

            return getAuthenticationManager()
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    credentials.getEmail(),
                                    credentials.getPassword(),
                                    Collections.emptyList()
                            )
                    );

        } catch (IOException e) {
            throw new ApiException("Attempt authentication error", e);
        }

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        final var username = ((User) authResult.getPrincipal()).getUsername();

        final var userDetails = userService.getUserDetailsByEmail(username);

        final var token = jwtUtils.generateToke(userDetails);

        response.addHeader("token", token);
        response.addHeader("userId", userDetails.getId().toString());
    }
}
