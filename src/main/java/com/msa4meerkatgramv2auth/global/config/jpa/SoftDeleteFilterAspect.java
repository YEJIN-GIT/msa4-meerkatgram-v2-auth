package com.msa4meerkatgramv2auth.global.config.jpa;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class SoftDeleteFilterAspect {
    // 모든 메소드들에 soft delete filter를 적용시키기 위한 aspect
    // 만일, aspect만들어 두지 않으면 매번 메소드 전에 부가기능을 넣어줘야함.
    private final EntityManager entityManager;  // JPA 관리 객체

    // @Before 언제: 핵심 비지니스 로직이 실행되기 전에 라는 의미의 `Advice`
    // within() 조인트컷: 특정 패키지 또는 클래스 내부에 속한 모든 메소드에 일괄 적용
    @Before("within(@org.springframework.web.bind.annotation.RestController *)")
    public void enableSoftDeleteFilter() {
        // 부가기능: JPA에 soft delete filter를 허용
        entityManager.unwrap(Session.class).enableFilter("softDelete"); // 세션이 활성화 된 동안에 soft delete를 허용하겠다
    }
}
