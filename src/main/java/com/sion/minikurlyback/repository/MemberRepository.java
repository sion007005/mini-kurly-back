package com.sion.minikurlyback.repository;

import com.sion.minikurlyback.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findOneByIdx(Long idx);
    Member findOneByMemberId(String memberId);
    Member findByEmail(String email);
}
