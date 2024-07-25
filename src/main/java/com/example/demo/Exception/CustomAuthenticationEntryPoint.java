 package com.example.demo.Exception;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

@Component
public final class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
	
    @Value("${jwt.access.token.cookie.name}")
    private String accessTokenName;
    
    @Value("${jwt.refresh.token.cookie.name}")
    private String refreshTokenName;

	@Override
	public void commence(final HttpServletRequest request, final HttpServletResponse response,
			final AuthenticationException authException) throws IOException {
		
		if(getRefreshToken(request) != null) {
//			addPathCookie(response, request.getServletPath());
//			String requestMethod
//			response.send
			
			response.sendRedirect(request.getContextPath() + "/api/nofilter/token-expired");
		}else {
			System.out.println("From Entry point: null:");
			response.sendRedirect(request.getContextPath() + "/api/nofilter/auth-entry-point");
		}
	}
	
	private void addPathCookie(HttpServletResponse httpServletResponse, String path) {
		Cookie cookie = new Cookie("path", path);
		cookie.setHttpOnly(true);
		cookie.setMaxAge(120);
		cookie.setSecure(false);
		httpServletResponse.addCookie(cookie);
	}
	
	
    private String getAccessToken(HttpServletRequest request){
        Cookie cookie = WebUtils.getCookie(request, accessTokenName);
        return cookie != null ? cookie.getValue():null;
    }
    
    private String getRefreshToken(HttpServletRequest request){
        Cookie cookie = WebUtils.getCookie(request, refreshTokenName);
        return cookie != null ? cookie.getValue():null;
    }

}