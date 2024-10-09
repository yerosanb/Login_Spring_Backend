 package com.example.demo.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import com.example.demo.jwt.JwtHelper;
import com.example.demo.mapper.AuthMapper;
import com.example.demo.model.Roles;

public class AccessTokenFilter extends OncePerRequestFilter {

	@Autowired
	private JwtHelper jwtHelper;

	@Autowired
	private AuthMapper mapper;

	@Value("${jwt.access.token.cookie.name}")
	private String cookieName;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			listCookies(request);
			String accessToken = getToken(request);
			System.out.println("Access token from doFilterInternal: " + accessToken);
			if (accessToken != null) {
				if (jwtHelper.validateAccessToken(accessToken)) {
					System.out.println("access token is valid");
					com.example.demo.model.User user = mapper
							.findByUserName2(jwtHelper.getEmailFromAccessToken(accessToken));

					UserDetails userDetails = new User(jwtHelper.getEmailFromAccessToken(accessToken), "",
							getAuthorities(user.getRoles()));
					UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(user, null,
							userDetails.getAuthorities());

					SecurityContextHolder.getContext().setAuthentication(upat);
				}else {
					System.out.println("redirecting to /secure/test3");
//					response.sendRedirect("/secure/test3");
				}
			}else {
				System.out.println("access token is null");
			}

		} catch (Exception e) {
			System.out.println("Exception occured: " + e.getClass().getName());
		}

		filterChain.doFilter(request, response);
	}
	
    private void listCookies(HttpServletRequest req) {
    	System.out.println("in list cookies: ");
        for (Cookie cookie : req.getCookies()) {
        	System.out.println("cookie: " + cookie.getName());
        }
        System.out.println("finished: ");
    }

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) {
		return request.getServletPath().startsWith("/api/nofilter");
	}

	private String getToken(HttpServletRequest request) {
		System.out.println("===Access token cookie name: ___" + cookieName);
		Cookie cookie = WebUtils.getCookie(request, cookieName);
		return cookie != null ? cookie.getValue() : null;
	}

	private Collection<? extends GrantedAuthority> getAuthorities(Collection<Roles> roles) {
		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(1);
		for (Roles role : roles) {
			authList.add(new GrantedAuthority() {
				@Override
				public String getAuthority() {
					// TODO Auto-generated method stub
					return role.getName();
				}
			});
		}
		return authList;
	}
}
