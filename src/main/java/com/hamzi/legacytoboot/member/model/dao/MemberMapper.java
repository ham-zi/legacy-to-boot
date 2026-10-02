package com.hamzi.legacytoboot.member.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.hamzi.legacytoboot.aop.CheckOne;
import com.hamzi.legacytoboot.member.model.dto.MemberDto;
import com.hamzi.legacytoboot.member.model.vo.Member;

@Mapper
public interface MemberMapper {
	public int signup(Member member);
	public Member findById(String userId);
	@CheckOne
	public int update(MemberDto member);
	@CheckOne
	public int delete(String userId);
}
