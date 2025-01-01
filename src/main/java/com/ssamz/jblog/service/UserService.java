package com.ssamz.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssamz.jblog.domain.RoleType;
import com.ssamz.jblog.domain.User;
import com.ssamz.jblog.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Transactional(readOnly = true)
	public User getUser(String username) {
		//검색 결과가 없을때 빈 User 객체 반환
		User findUser = userRepository.findByUsername(username).orElseGet(()-> {
						return new User(); 
		});
		
		return findUser;
	}
	
	@Transactional
	public void insertUser(User user) {
		//비밀번호를 암호화하여 설정한다.
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
	}
	
	@Transactional
	public User updateUser(User user) {
		User findUser = userRepository.findById(user.getId()).get();
		findUser.setUsername(user.getUsername()); 
		findUser.setPassword(passwordEncoder.encode(user.getPassword()));  
		findUser.setEmail(user.getEmail()); 
		
		return findUser;
	}
}
