package com.jypshop.controller;

import com.jypshop.dto.MemberDto;
import com.jypshop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by qkrwpdud1@gmail.com on 2020/03/08
 * Github : http://github.com/jypweback
 * Description : 회원 controller
 */

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class MemberApiController {

    private final MemberService memberService;

    /**
     * 회원가입
     * @param memberDto
     * @return
     */
    @PostMapping("/member/join")
    public MemberDto createMember(@Valid @RequestBody MemberDto memberDto){

        return memberService.createMember(memberDto);
    }

    /**
     * 회원수정
     * @param id
     * @param memberDto
     * @return
     */
    @PutMapping("/member/{id}")
    public MemberDto updateMember(@PathVariable Long id, @Valid @RequestBody MemberDto memberDto){

        return memberService.updateMember(id, memberDto);
    }

    /**
     * 회원조회
     * @param id
     * @return
     */
    @GetMapping("/member/{id}")
    public MemberDto getMember(@PathVariable Long id){

        return memberService.getMember(id);
    }

    /**
     * 회원삭제
     * @param id
     */
    @DeleteMapping("/member/{id}")
    public void removeMember(@PathVariable Long id){

        memberService.removeMember(id);
    }

}
