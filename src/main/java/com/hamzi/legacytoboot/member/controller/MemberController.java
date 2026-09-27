package com.hamzi.legacytoboot.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.hamzi.legacytoboot.member.model.dto.MemberDto;
import com.hamzi.legacytoboot.member.model.service.MemberService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Controller
@Slf4j
public class MemberController {
	private final MemberService memberService;
	
	
	@GetMapping("/signup")
	public String signupForm() {
		return "member/signup";
	}
	
	@PostMapping("members")
	public String signup(@Valid MemberDto member) {
		memberService.signup(member);
		return "index";
	}
	
}
