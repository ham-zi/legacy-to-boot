package com.hamzi.legacytoboot.member.model.vo;

import lombok.Builder;
import lombok.Setter;
import lombok.ToString;

@Builder
@Setter
@ToString
public class Member {
	private String userId;
	private String userPwd;
	private String userName;
	private String email;
}
