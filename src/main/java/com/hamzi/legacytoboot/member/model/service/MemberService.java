package com.hamzi.legacytoboot.member.model.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hamzi.legacytoboot.member.model.dao.MemberMapper;
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
}
