package com.jypshop.domain;

import com.jypshop.repository.MemberRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by qkrwpdud1@gmail.com on 2020/03/09
 * Github : http://github.com/jypweback
 * Description :
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberTest {

    @Autowired
    private MemberRepository memberRepository;

    @After
    public void cleanUp(){
        memberRepository.deleteAll();
    }

    @Test
    public void 회원등록_테스트(){

        //given
        String name = "박제영";
        memberRepository.save(
                Member
                        .builder()
                        .name(name)
                        .address(
                                Address
                                        .builder()
                                        .city("city")
                                        .street("street")
                                        .zipcode("zipcode")
                                        .build()).build());

        // when
        List<Member> members = memberRepository.findAll();

        // then
        Member member = members.get(0);
        assertThat(member.getName()).isEqualTo(name);
    }

    @Test
    @Transactional
    public void 회원업데이트_테스트(){

        // given
        String updateName = "박제영 update";
        Member savedMember =
                memberRepository.save(
                Member
                        .builder()
                        .name("박제영")
                        .address(
                            Address
                            .builder()
                            .city("city")
                            .street("street")
                            .zipcode("zipcode")
                            .build()
                        )
                        .build());

        // when
        savedMember.update(Member.builder().name(updateName).build());

        // then
        assertThat(savedMember.getName()).isEqualTo(updateName);

    }
}