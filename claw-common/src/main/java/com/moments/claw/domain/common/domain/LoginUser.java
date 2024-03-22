package com.moments.claw.domain.common.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.moments.claw.domain.base.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"username","password","enabled","accountNonExpired", "accountNonLocked", "credentialsNonExpired", "authorities"})
public class LoginUser implements UserDetails, Serializable {
	private static final long serialVersionUID = 48961531894131221L;

	private User user;

	private List<String> permissions;

	// 权限校验
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
