package repository;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("test");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
      //  Assertions.assertEquals(member, result);
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("test1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("test2");
        repository.save(member2);

     //   Optional<Member> test2 = repository.findByName("test2");
        Member result = repository.findByName("test1").get();
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findByAll(){
        Member member1 = new Member();
        member1.setName("test11");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("test22");
        repository.save(member2);

        List<Member> result = repository.findByAll();
        assertThat(result.size()).isEqualTo(2);
    }

}
