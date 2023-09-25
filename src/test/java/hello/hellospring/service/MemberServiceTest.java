package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
///ctrl shift t
//
//    MemberService memberService = new MemberService();
//    MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void join() {
        // given 주어졌을때
        Member member = new Member();
        member.setName("test");

        //when 실행이 되고
        Long saveId = memberService.join(member);

        //then 결과가 나온다 ctrl alt v
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());

    }

    @Test
    void 중복_test() {
        Member member1 = new Member();
        member1.setName("test");

        Member member2 = new Member();
        member2.setName("test");

        memberService.join(member1);

//        try {
//            memberService.join(member2);
//            fail();
//        } catch (IllegalStateException e){
//            assertThat(e.getMessage()).isEqualTo("존재함2");
//        }

        //예외가 터져야함
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("존재함");
        //     assertThrows(NullPointerException.class, ()->memberService.join(member2));


    }
    
    @Test
    void findMember() {
    }

    @Test
    void findOne() {
    }
}