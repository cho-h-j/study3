package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface MemberRepository {
    //컨트롤러 외부요청받고
    // 서비스 비지니스로직만들고
    ///repository :  구현체(데이터저장)
    Member save(Member member);
    Optional<Member> findById(Long id);

    List<Member> findAll();

    Optional<Member> findByName(String name);
    List<Member> findByAll();
}
