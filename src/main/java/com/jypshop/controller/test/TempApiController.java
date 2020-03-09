package com.jypshop.controller.test;

import com.jypshop.dto.MemberDto;
import com.jypshop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by qkrwpdud1@gmail.com on 2020/03/09
 * Github : http://github.com/jypweback
 * Description :
 */

@RequiredArgsConstructor
@RestController
public class TempApiController {

    private final MemberService memberService;

    @GetMapping("/temp/insert")
    public MemberDto tempMemberInsert(){
        return memberService.createMember(MemberDto.builder()
                .name("박제영")
                .city("city")
                .street("street")
                .zipcode("zip code")
                .build()
        );
    }

}
