package net.scit.spring7.handler;

import java.io.IOException;
import java.net.URLEncoder;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	
	@Override
	public void onAuthenticationFailure(
			HttpServletRequest request,
			HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		String errMessage = "";
		
		if(exception instanceof BadCredentialsException) {
			errMessage += "아이디나 비밀번호가 잘못되었습니다.";
			errMessage += exception.getMessage();
		} else {
			errMessage += "로그인 실패했습니다. 관리자에게 문의하세요";
			errMessage += exception.getMessage();
		}
		errMessage = URLEncoder.encode(errMessage,"UTF-8"); //에러메시지가 한글이므로
		response.sendRedirect("/user/login?error=true&errMessage="+errMessage);
		// super.onAuthenticationFailure(request, response, exception);
	}

	
}
