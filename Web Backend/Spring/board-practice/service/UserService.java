package net.scit.spring7.service;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.scit.spring7.dto.UserDTO;
import net.scit.spring7.entity.UserEntity;
import net.scit.spring7.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	/**
	 * 전달받은 userId가 존재
	 * @param userId가 DB에 존재하는지 여부 확인
	 * @return
	 */
	public boolean existId(String userId) {
		boolean result = userRepository.existsById(userId);
		log.info("아이디 존재여부: {}",  result);	// 가입하려면 false
		
		return !result;
	}
	
	/**
	 * 회원 가입 처리
	 * @param userDTO
	 * @return
	 */
	public boolean joinProc(UserDTO userDTO) {
		// 비밀번호 암호화
		
		userDTO.setUserPwd(bCryptPasswordEncoder.encode(userDTO.getUserPwd()));
		
		UserEntity entity = UserEntity.toEntity(userDTO);
		userRepository.save(entity);	// 회원가입 완료
		
		boolean result = userRepository.existsById(userDTO.getUserId()); 
		
		return false;
	}
	
	/**
	 * 입력한 비밀번호가 맞는지 확인
	 * @param userDTO
	 * @return
	 */
	public UserDTO pwdCheck(String userId, String userPwd) {
		Optional<UserEntity> temp = userRepository.findById(userId);
		if(temp.isPresent()) {
			UserEntity entity = temp.get();
			String encodedPwd = entity.getUserPwd();	//암호화된 비번
			
			boolean result = bCryptPasswordEncoder.matches(userPwd, encodedPwd);	//DB암호
			if(result) {
				return UserDTO.toDTO(entity);
			}
		}
		
		return null;
	}
	
	/**
	 * DB에서 개인정보 수정
	 * @param userDTO
	 */
	@Transactional
	public void updateProc(UserDTO userDTO) {
		String id = userDTO.getUserId();
		
		Optional<UserEntity> temp = userRepository.findById(id);
		if(temp.isPresent()) {
			UserEntity entity = temp.get();
			
			// 사용자가 입력한 정보 (비번과 이메일)
			String encodedPwd = bCryptPasswordEncoder.encode(userDTO.getUserPwd());
			String email = userDTO.getEmail();
			
			// DB에서 뽑아낸 정보 (비번과 이메일)
			entity.setUserPwd(encodedPwd);
			entity.setEmail(email);
		}
	}
}
