package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
//@Transactional
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    void join() {
        // given 주어졌을때
        Member member = new Member();
        member.setName("dsdsd~");

        //when 실행이 되고
        Long saveId = memberService.join(member);

        //then 결과가 나온다 ctrl alt v
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());

    }

    @Test
    void 중복_test() {
        Member member1 = new Member();
        member1.setName("aa");

        Member member2 = new Member();
        member2.setName("ddd");

        memberService.join(member1);

        //예외가 터져야함
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("존재함");
        //     assertThrows(NullPointerException.class, ()->memberService.join(member2));
    }
}