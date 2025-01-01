package com.ssamz.jblog.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssamz.jblog.domain.User;
import com.ssamz.jblog.dto.ResponseDTO;
import com.ssamz.jblog.dto.UserDTO;
import com.ssamz.jblog.repository.UserRepository;
import com.ssamz.jblog.security.UserDetailsImpl;
import com.ssamz.jblog.service.UserService;

import jakarta.validation.Valid;
 

@Controller
public class UserController {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("/auth/insertUser")
	public String insertUser() {
		return "user/insertUser";
	}
	
	@GetMapping("/auth/login")
	public String login() {
		return "system/login";
	}
	 
	@PostMapping("/auth/insertUser")
	public @ResponseBody ResponseDTO<?> insertUser(
			@Valid @RequestBody UserDTO userDTO, BindingResult bindingResult){
		
		//UserDTO -> User 객체로 반환
		User user = modelMapper.map(userDTO, User.class);
		User findUser = userService.getUser(user.getUsername());
		
		if(findUser.getUsername() == null) {
			userService.insertUser(user);
			return new ResponseDTO<> (HttpStatus.OK.value(), user.getUsername() + " 가입 성공!");
		} else {
			return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), user.getUsername() + "님은 이미 회원입니다.");
		}		
	}
	
	@GetMapping("/user/updateUser")
	public String updateUser() {
		return "user/updateUser";
	}
	
	@PutMapping("/user") 
	public @ResponseBody ResponseDTO<?> updateUser(@RequestBody User user,
			@AuthenticationPrincipal UserDetailsImpl principal){
		//회원정보 수정과 동시에 세션 갱신
		principal.setUser(userService.updateUser(user));		
		
		return new ResponseDTO<>(HttpStatus.OK.value(), user.getUsername() + " 수정 완료");	
	}
	
}
