package com.hamzi.legacytoboot.member.model.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hamzi.legacytoboot.exception.NotFoundException;
import com.hamzi.legacytoboot.member.model.dao.MemberMapper;
import com.hamzi.legacytoboot.member.model.dto.LoginDto;
import com.hamzi.legacytoboot.member.model.dto.MemberDto;
import com.hamzi.legacytoboot.member.model.vo.Member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {
	private final PasswordEncoder passwordEncoder;
	private final MemberMapper memberMapper;
	
	public void signup(MemberDto member) {
		Member userInfo = Member.builder()
								.userId(member.getUserId())
								.userPwd(passwordEncoder.encode(member.getUserPwd()))
								.userName(member.getUserName())
								.email(member.getEmail())
								.build();
		memberMapper.signup(userInfo);			    
	}

	public Member login(LoginDto loginInfo) {
		Member userInfo = memberMapper.findById(loginInfo.getUserId());
		if(userInfo == null) {
			throw new NotFoundException("존재하지 않는 아이디입니다.");
		}
		if(passwordEncoder.matches(loginInfo.getUserPwd(), userInfo.getUserPwd())) {
			return userInfo;
		}			
		return null;
	}

	public void update(MemberDto member, String userId) {
		vaildateUserId(member, userId);
		vaildateUpdateRequest(member);
		if(memberMapper.update(member) != 1) {
			throw new NotFoundException("잘못된 요청입니다.");
		}
	}
	
	private void vaildateUpdateRequest(MemberDto member) {
		vaildateEmail(member.getEmail());
		vaildateUserName(member.getUserName());
	}
	
	private void vaildateUserId(MemberDto member, String userId) {
		if(!userId.equals(member.getUserId())) {
			throw new NotFoundException("올바르지 않은 요청입니다.");
		}
	}
	
	private void vaildateEmail(String email) {
		String regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)+$";
		if(!email.matches(regexp)) {
			throw new NotFoundException("잘못된 이메일 입력입니다.");
		}
	}
	
	private void vaildateUserName(String userName) {
		String regexp = "^[0-9가-힣a-z%]{2,10}$";
		if(!userName.matches(regexp)) {			
			throw new NotFoundException("잘못된 닉네임 입력입니다.");
		}
	}
}
