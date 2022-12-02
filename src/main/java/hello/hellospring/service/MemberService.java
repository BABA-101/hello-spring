package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
    private final MemberRepository memberRepository; // = new MemoryMemberRepository();
    //생성자 추가. test. new해서 인스턴스를 생성하는 것이 아니라 외부에서 넣어주도록 변경함
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    /**
     *  회원가입
     */
    public Long join(Member member){
        // ifPresent null이 아니라 값이 있으면
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()).ifPresent(m-> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
