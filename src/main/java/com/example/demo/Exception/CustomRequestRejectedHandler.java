package com.example.demo.Exception;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.security.web.firewall.RequestRejectedHandler;
  
public class CustomRequestRejectedHandler implements RequestRejectedHandler {

	private static final Logger LOG = LoggerFactory.getLogger(CustomRequestRejectedHandler.class);

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			RequestRejectedException requestRejectedException) throws IOException, ServletException {
		System.out.println("hoollllaaa working....");
		response.sendRedirect(request.getContextPath() + "/api/nofilter/request-rejected");
	}
}