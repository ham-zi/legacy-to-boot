package com.hamzi.legacytoboot.member.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.hamzi.legacytoboot.member.model.vo.Member;

@Mapper
public interface MemberMapper {
	public int signup(Member member);
}
