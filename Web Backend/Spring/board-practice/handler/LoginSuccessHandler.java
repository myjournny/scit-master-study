package net.scit.spring7.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(
			HttpServletRequest request
			, HttpServletResponse response
			,Authentication authentication) throws IOException, ServletException {
		
		List<String> roleNames = new ArrayList<>();
		
		// 한 명의 유저가 여러 Role 정보를 가질 수 있도록 처리
		authentication.getAuthorities().forEach((auth) -> 
				roleNames.add(auth.getAuthority())
		);
		
		// 사용자의 ROLE 정보에 따라 리다이렉트 정보가 달라짐
		if(roleNames.contains("ROLE_ADMIN")) {
			response.sendRedirect("/admin/adminpage");
		} else if(roleNames.contains("ROLE_USER")) {
			response.sendRedirect("/");
		}
	}

}
