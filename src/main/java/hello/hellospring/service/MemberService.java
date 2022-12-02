package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class MemberService {
    private final MemberRepository memberRepository; // = new MemoryMemberRepository();
    //생성자 추가. test. new해서 인스턴스를 생성하는 것이 아니라 외부에서 넣어주도록 변경함
    // MemberService는 MemberRepository가 필요함. autowired가 존재하면, 스프링이 MemberSerivce를 생성할 때 스프링 컨테이너에 MemberServcie등록하면서.. Autowired 존재확인 후 스프링 컨테이너의 서비스에 MemberRepository넣어줌
    @Autowired
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
