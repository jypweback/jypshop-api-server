package com.jypshop.repository;

import com.jypshop.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by qkrwpdud1@gmail.com on 2020/03/08
 * Github : http://github.com/jypweback
 */

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
}
