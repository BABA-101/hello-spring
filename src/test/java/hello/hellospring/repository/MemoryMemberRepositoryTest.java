package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);
        Member result = repository.findById(member.getId()).get(); //id세팅, 반환타입 Optional임. 이거 꺼낼 때 Get()
        // 아래와 같이 해서 출력하여 비교할 수도 있겠지만,
        //System.out.println("result = "+ (result == member));

        // Assertions.assertEquals(member,result); //이렇게 하면.. 다를 시 에러발생
        // 그러나 요즘엔 이렇게 사용

    }
}
