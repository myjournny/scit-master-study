package net.scit.spring7.handler;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler{

	@Override
	public void onLogoutSuccess(
			HttpServletRequest request
			, HttpServletResponse response
			, Authentication authentication) throws IOException, ServletException {
			
		String refererUrl = request.getHeader("Referer");
		if(refererUrl != null) {
			response.sendRedirect(refererUrl); // 이전 페이지로 리다이렉트
		} else {
			response.sendRedirect("/");	// 리퍼러가 없을 경우 기본 URL로 리다이렉트
		}
		
		
	}
	
	

}
