package com.example.demo.config;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
public class CookieUtil {

	public static void create(HttpServletResponse httpServletResponse, String name, String value, Boolean secure,
			String maxAge, String domain) {
		
		Cookie cookie = new Cookie(name, value);
		cookie.setSecure(secure);
		cookie.setHttpOnly(true);
		cookie.setMaxAge(convert_max_age(Long.parseLong(maxAge)));
		cookie.setDomain(domain);
		cookie.setPath("/");
		cookie.setSecure(secure);
		httpServletResponse.addCookie(cookie);
		
		if(name==null) {
			System.out.println("it is nullllllllllllllll");
		}
		
		else {
			System.out.println("it is notttttttttttttttt null");

		}
			
	}
	
	public static void clear(HttpServletResponse httpServletResponse, String name) {
        Cookie cookie = new Cookie(name, null);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(1);
        cookie.setDomain("localhost");
        httpServletResponse.addCookie(cookie);
	}
	
	public static int convert_max_age(long age) {
		return (int) (age/1000);
	}
}
