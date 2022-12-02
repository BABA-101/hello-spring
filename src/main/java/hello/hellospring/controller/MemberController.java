package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {
    private final MemberService memberService;
    //생성자에 Autowired가 있으면, 스프링 컨테이너에 있는 멤버서비스를 가져다 연결시켜줌
    //스프링 컨테이너에 helloController는 등록이 됨. memberService 클래스는 순수한 자바 클래스야 스프링이 얘 존재를 몰라. > 가서 @service추가, 리포지토리 구현부가서도 @Repository 추가
    // 정리하면, Autowired를 사용 시 MemberController가 생성이 될 때 스프링 빈에 등록되어 있는 MemberService 객체를 가져다가 넣어준다. >> 디펜던시 인젝션(DI). 의존관계를 넣어주는 것.
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
