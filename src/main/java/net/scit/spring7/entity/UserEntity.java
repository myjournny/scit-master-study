package net.scit.spring7.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.scit.spring7.dto.UserDTO;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name="board_user")
public class UserEntity {
	@Id
	@Column(name="user_id")
	private String userId;
	
	@Column(name="user_pwd", nullable = false)
	private String userPwd;
	
	@Column(name="user_name", nullable = false)
	private String userName;
	
	@Column(name="email")
	private String email;
	
	@Column(name="roles")
	@Builder.Default
	private String roles = "ROLE_USER";
	
	@Column(name="enabled")
	@Builder.Default
	private Boolean enabled = true;
	
	public static UserEntity toEntity(UserDTO userDTO) {
		return UserEntity.builder()
				.userId(userDTO.getUserId())
				.userPwd(userDTO.getUserPwd())
				.userName(userDTO.getUserName())
				.email(userDTO.getEmail())
//				.roles(userDTO.getRoles())
//				.enabled(userDTO.getEnabled())
				.build();
				
	}
}
