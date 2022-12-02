package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach  //clear용. 이렇게 테스트코드 내 적어주면 테스트마다 끝날 때마다 실행
    public void afterEach(){
        repository.clearStore(); // 클리어 함수 호출 ★
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);
        Member result = repository.findById(member.getId()).get(); //id세팅, 반환타입 Optional임. 이거 꺼낼 때 Get()
        // 아래와 같이 해서 출력하여 비교할 수도 있겠지만,
        //System.out.println("result = "+ (result == member));
        Assertions.assertThat(member).isEqualTo(result); // member가 result랑 같으면 true 에러면 걍 러닝이안돼
    }

    @Test
    public void findByName(){
        //이름으로 찾는거 테스트
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2  = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        Assertions.assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2  = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        Assertions.assertThat(result.size()).isEqualTo(2);
    }
    // clear 없이 전체 테스트 시 에러 발생
    // 메서드 실행 순서가 없기 때문에 객체가 겹치는 것
    // 테스트는 서로 순서관계 없이, 의존관계 없이 설계되어야 함. ★
    // 그러기 위해서느 하나의 테스트가 끝날 때마다 저장소나 공용 저장소 이런 곳을 깨끗하게 지워줘야 함.
}
