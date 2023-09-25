package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {

///    private final MemberRepository memberRepository =  new MemoryMemberRepository();

    private final MemberRepository memberRepository;


    public MemberService(MemberRepository memberRepository) {

        this.memberRepository = memberRepository;
    }

    public Long join(Member member){

//        Optional<Member> result = memberRepository.findByName(member.getName());
//        result.ifPresent(m->{
//            throw new IllegalStateException("존재함");
//        });
        long start = System.currentTimeMillis();
        try {
            validateDuplicateMember(member); //중복검사
            memberRepository.save(member);
            return member.getId();
        } finally {
            long finish = System.currentTimeMillis();
            long time = finish - start;
            System.out.println( " join : " + time +"ms");
        }


    }

    private void validateDuplicateMember(Member member) {
        //ctrl alt m
        memberRepository.findByName(member.getName())
               .ifPresent(m->{
                   throw new IllegalStateException("존재함");
               });
    }

    public List<Member> findMember(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

}
