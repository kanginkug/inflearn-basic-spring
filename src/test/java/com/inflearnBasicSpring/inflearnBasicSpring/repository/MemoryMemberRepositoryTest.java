package com.inflearnBasicSpring.inflearnBasicSpring.repository;

import com.inflearnBasicSpring.inflearnBasicSpring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    //test클래스는 순서가 뒤죽박죽이기 때문에 테스트가 끝날때마다 repository를 지워줘야 에러가 나지 않는다.
    @AfterEach
    public void afterEach(){
        repository.afterEach();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);
        Member result = repository.findById(member.getId()).get();
        // alt + enter를 한후 add on-demand static import for~를 클릭하면 assertThat만 사용할 수 있게된다.
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findId(){
        Member member1 = new Member();
        member1.setName("inkug1");
        repository.save(member1);

        //위의 member1을 복사했기 때문에 변수명만 바꿔주면 되는 상황에서 shift + F6을 누르면 동시에 같은 변수명들을 바꿔줄 수 있다.
        Member member2 = new Member();
        member2.setName("inkug2");
        repository.save(member2);


        // repository.findByName("inkug1").get(); 까지만 쓰고 ctrl + alt + v를 누르면  Member result = 가 자동으로 생성된다.
        // repository.findByName("inkug1") 까지만 쓰면 Optional<Member> inkug1 = 가 생성되고
        // .get()을 붙이면  Member inkug1 = 가 생성된다.
        Member result = repository.findByName("inkug1").get();
        assertThat(member1).isEqualTo(result);
    }

    @Test
    public void findAll(){
        Member member = new Member();
        member.setName("inkug1");
        repository.save(member);

        Member member2 = new Member();
        member2.setName("inkug2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }
}
