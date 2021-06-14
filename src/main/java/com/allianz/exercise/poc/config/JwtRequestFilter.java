package com.allianz.exercise.poc.config;

import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;
import java.util.function.Function;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

/**
 * The Class JwtRequestFilter.
 */
public class JwtRequestFilter extends GenericFilterBean {
	private String secret;

	private static final String BEARER = "Bearer";

	private UserDetailsService userDetailsService;

	/**
	 * Instantiates a new jwt request filter.
	 *
	 * @param userDetailsService the user details service
	 * @param secret the secret
	 */
	public JwtRequestFilter(UserDetailsService userDetailsService, String secret) {
		this.userDetailsService = userDetailsService;
		this.secret = Base64.getEncoder().encodeToString(secret.getBytes());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 * javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
			throws IOException, ServletException {

		String headerValue = ((HttpServletRequest) req).getHeader("Authorization");
		getBearerToken(headerValue).ifPresent(token -> {
			String username = getClaimFromToken(token, Claims::getSubject);
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);

			if (username.equals(userDetails.getUsername()) && !isJwtExpired(token)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails((HttpServletRequest) req));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		});

		filterChain.doFilter(req, res);
	}

	/**
	 * Gets the bearer token.
	 *
	 * @param headerVal
	 *            the header val
	 * @return the bearer token
	 */
	private Optional<String> getBearerToken(String headerVal) {
		if (headerVal != null && headerVal.startsWith(BEARER)) {
			return Optional.of(headerVal.replace(BEARER, "").trim());
		}
		return Optional.empty();
	}

	/**
	 * Gets the claim from token.
	 *
	 * @param <T>
	 *            the generic type
	 * @param token
	 *            the token
	 * @param claimsResolver
	 *            the claims resolver
	 * @return the claim from token
	 */
	private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		return claimsResolver.apply(claims);
	}

	/**
	 * Checks if is jwt expired.
	 *
	 * @param token
	 *            the token
	 * @return the boolean
	 */
	private Boolean isJwtExpired(String token) {
		Date expirationDate = getClaimFromToken(token, Claims::getExpiration);
		return expirationDate.before(new Date());
	}
}