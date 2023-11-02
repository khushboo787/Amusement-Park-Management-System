package com.JoyLand.config;

import java.io.IOException;

import javax.crypto.SecretKey;
import com.JoyLand.config.SecurityConstants;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;



import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtTokenValidatorFilter extends OncePerRequestFilter {

	  @Override
	    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
	            throws ServletException, IOException {

	        String authorizationHeader = request.getHeader(SecurityConstants.JWT_HEADER);
	        String jwt = null;

	        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer")) {

	            authorizationHeader = authorizationHeader.trim();

	            try {

	                jwt = authorizationHeader.substring(7); // extracting the word "Bearer"

	                JwtParser jwtParser = Jwts.parserBuilder()
	                        .setSigningKey(Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes()))
	                        .build();

	                Jwt<Header<?>, Claims> token = (Jwt<Header<?>, Claims>) jwtParser.parse(jwt);

	                Authentication auth = new UsernamePasswordAuthenticationToken(
	                        token.getBody().getSubject(),
	                        null,
	                        AuthorityUtils.commaSeparatedStringToAuthorityList(token.getBody().get("authorities").toString())
	                );

	                SecurityContextHolder.getContext().setAuthentication(auth);
	            }
	            catch (MalformedJwtException e) {

	                // Below response is not being sent
	                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid JWT token: " + jwt);
	                return;
	            }
	            catch (ExpiredJwtException e) {

	                // Below response is not being sent
	                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Expired JWT token: " + jwt);
	                return;
	            }
	            catch (IllegalArgumentException e) {

	                // Below response is not being sent
	                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "JWT cannot be null or empty or only whitespace");
	                return;
	            }
	            catch (Exception e) {

	                // Below response is not being sent
	                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	                return;
	            }
	        }

	        filterChain.doFilter(request, response);
	    }
	//this time this validation filter has to be executed for all the apis except the /signIn api
	
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
	
		return request.getServletPath().equals("/signIn");
	}

}
