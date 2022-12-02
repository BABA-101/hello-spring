package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence); //member에 id 값 셋팅 (name은 사용자가, id는 시스템이 정해줌)
        store.put(member.getId(),member); //스토어에 저장
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); //null이 반환될 가능성이 있다면 Optional로 감싸
    }

    @Override
    // member의 getName() 반환값이 매개변수 name과 같은지 비교
    // fundAny(): 하나라도 찾는 함수
    // 아무튼 하나 찾아지면 걔를 그거 반환하는 것, 없으면 null포함되어서 반환
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name)).findAny();
    }

    @Override
    public List<Member> findAll() {
        //store에 있는 멤버들 반환
        return new ArrayList<>(store.values());
    }

    // 클리어용 함수
    public void clearStore(){
        store.clear();
    }
}
//작성하다 잘 돌아가는지 테스트 하고싶다? 이제 src/test를 이용 => 테스트 케이스

