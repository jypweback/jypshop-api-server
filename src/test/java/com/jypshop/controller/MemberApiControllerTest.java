package com.jypshop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jypshop.domain.Member;
import com.jypshop.dto.MemberDto;
import com.jypshop.repository.MemberRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by qkrwpdud1@gmail.com on 2020/03/09
 * Github : http://github.com/jypweback
 * Description :
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MemberApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MemberRepository memberRepository;

    private MockMvc mvc;

    @Before
    public void setup(){
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @After
    public void tearDown() throws Exception{
        memberRepository.deleteAll();
    }

    @Test
    public void 회원_등록() throws Exception{

        // given
        final String name = "박제영";
        final String url = "http://localhost:" + port + "/api/v1/member/join";
        MemberDto memberDto = MemberDto.builder().name(name).build();

        // when
        ResultActions actions = mvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(memberDto))
        );

        // then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("박제영"));

    }

    @Test
    public void 회원_업데이트() throws Exception{

        // given
        final String updateName = "업데이트_박제영";
        Member savedMember = memberRepository.save(Member.builder().name("박제영").build());
        MemberDto updateMemberDto = MemberDto.builder().name(updateName).build();
        final String url = "http://localhost:" + port + "/api/v1/member/" + savedMember.getId();


        // when
        ResultActions actions = mvc.perform(put(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(updateMemberDto))
        );

        // then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value(updateName));

    }

    @Test
    public void 회원_삭제() throws Exception{

        // given
        Member savedMember = memberRepository.save(Member.builder().name("박제영").build());
        final String url = "http://localhost:" + port + "/api/v1/member/" + savedMember.getId();

        // when
        ResultActions actions = mvc.perform(delete(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
        );

        // then
        actions
                .andExpect(status().isOk());

    }


}