package net.scit.spring7.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.scit.spring7.dto.LoginUserDetails;
import net.scit.spring7.entity.UserEntity;
import net.scit.spring7.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginUserDetailsService implements UserDetailsService {
	private final UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userId)
			throws UsernameNotFoundException {
		// Optional 반환하는 메소드 orElseThrow() 연결해서 사용가능
		UserEntity userEntity = userRepository.findById(userId)
			.orElseThrow(() -> {
				throw new UsernameNotFoundException("ID나 비밀번호가 틀렸습니다.");
			});
		
		
		return LoginUserDetails.toDTO(userEntity);
	}

}
