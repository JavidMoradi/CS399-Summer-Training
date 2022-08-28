package com.summertraining.iysproject.jwt;

import com.summertraining.iysproject.model.Person;
import com.summertraining.iysproject.model.Role;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtTokenFilter extends OncePerRequestFilter { // OncePerRequestFilter is to guarantee the single execution per request
    @Autowired private JwtTokenUtil jwtTokenUtil;

    /**
     * If the Authorization header of the request does not contain a Bearer token,
     * it continues the filter chain without updating authentication context.
     * Else, if the token is not verified, continue the filter chain without updating authentication context.
     * If the token is verified, update the authentication context with the user details ID and username.
     * In other words, it tells Spring that the user is authenticated, and continue the downstream filters.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        if (!hasAuthorizationHeader(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        String accessToken = getAccessToken(request);

        if (!jwtTokenUtil.validateAccessToken(accessToken)) {
            filterChain.doFilter(request, response);
            return;
        }

        setAuthenticationContext(accessToken, request);
        filterChain.doFilter(request, response);
    }

    private void setAuthenticationContext(String accessToken, HttpServletRequest request) {
        UserDetails userDetails = getUserDetails(accessToken);

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

    /**
     * Create a new user via access token's Payload/Data section
     * Payload contains id, name, issued at, etc. information
     * @param accessToken token to be get payload data from
     * @return new person with token's payload field information
     */
    private UserDetails getUserDetails(String accessToken) {
        Person userDetails = new Person();

        Claims claims = jwtTokenUtil.parseClaims(accessToken);

        String claimRoles = (String) claims.get("roles");
        claimRoles = claimRoles.replace("[", "").replace("]", "");

        System.out.println("CLAIM ROLE IS: " + claimRoles);

        String[] roleNames = claimRoles.split(",");

        for (String role : roleNames) {
            userDetails.addRoleToPerson(new Role(role));
        }
        
        String subject = (String) claims.get(Claims.SUBJECT);
        String[] subjectArr = subject.split(",");
        // String[] subjectArr = jwtTokenUtil.getSubject(accessToken).split(",");

        userDetails.setId((long) Integer.parseInt(subjectArr[0]));
        userDetails.setUsername(subjectArr[1]);

        return userDetails;
    }

    /**
     * Check if the Authorization header is valid or not
     * @param request the request's to be sent to the api endpoint
     * @return false if Authorization header is null or doesn't start with "Bearer," else true
     */
    private boolean hasAuthorizationHeader(HttpServletRequest request) {
        String header = request.getHeader("Authorization");

        if (ObjectUtils.isEmpty(header) || !header.startsWith("Bearer")) {
            return false;
        }

        return true;
    }

    /**
     * Get the access token from the request's header
     */
    private String getAccessToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        String token = header.split(" ")[1].trim();

        return token;
    }
}
