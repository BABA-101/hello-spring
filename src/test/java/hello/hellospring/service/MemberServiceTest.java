package hello.hellospring.service;
import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

class MemberServiceTest {
    // 테스트를 하려면 서비스가 있어야겠지
    MemberService memberService; //=new MemberService();
    //클리어 위해 메모리멤버리포짓 가져오나, MemberService와 MemoryMemberRepository라는 서로 다른 인스턴스 만들어서 쓰긴 좀 그래.
    // MemberService에서 어차피 MemoryMemberRepository 생성함 (상수). 물론 상수라서 문제는 없겠지만, 상수가 아니면 문제가됨
    // 같은 인스턴스로 테스트해야함 => 그걸 위해 MemberService에 생성자추가
    //MemoryMemberRepository repository = new MemoryMemberRepository();
    MemoryMemberRepository memberRepository;
    @BeforeEach
    public void beforeEach(){ //이렇게하면 테스트 실행 전에 처리해서 같은 메모리 리포지토리를 사용하게됨 (같은 인스턴스)
        // 디펜던시 인젝션(DI)이라고 함. 멤버서비스 입장에서 멤버리포지토리를 외부에서 넣어주기
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }
    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void join() {
        //given 이 데이터를 기반으로
        Member member = new Member();
        member.setName("spring");


        //when 이 코드를 검증하는군
        Long saveID=memberService.join(member);

        //then 검증부
        Member findMember = memberService.findOne(saveID).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void validateDuplicateMember() {
        //given
        Member member1 = new Member();
        member1.setName("spring");
        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));// 뒤 로직을 실행할 건데, IllegalStateException클래스가 발생되어야 함(해당 예외가 터져야한다)
/*      try-catch문 쓰는게 번거롭다고 함. 위 로직을 사용하라
        try{
            memberService.join(member2);W
            fail();
        }catch (IllegalStateException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }*/
        assertThat(e.getMessage()).isEqualTo(("이미 존재하는 회원입니다."));
        //then
    }

    @Test
    void findOne() {
    }
}