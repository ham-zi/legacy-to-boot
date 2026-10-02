package com.hamzi.legacytoboot.aop;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.hamzi.legacytoboot.exception.NotFoundException;

@Aspect
@Component
public class ResultCheckAspect {

	@AfterReturning(
			pointcut = "execution(int com.hamzi.legacytoboot.member.model.dao.MemberMapper.update(..))",
			returning = "result",
			argNames = "result"
	)
	public void checkResult(int result) {
		if (result != 1) {
			throw new NotFoundException("잘못된 요청입니다.");
		}
	}
}
