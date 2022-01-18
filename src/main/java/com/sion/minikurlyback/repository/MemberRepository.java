package com.sion.minikurlyback.repository;

import com.sion.minikurlyback.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    public Member findOneByIdx(Long idx);
    public Member findOneByMemberId(String memberId);
}
