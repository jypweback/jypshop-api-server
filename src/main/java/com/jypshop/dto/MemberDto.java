package com.jypshop.dto;

import com.jypshop.domain.Address;
import com.jypshop.domain.Member;
import lombok.Builder;
import lombok.Data;

/**
 * Created by qkrwpdud1@gmail.com on 2020/03/08
 * Github : http://github.com/jypweback
 */

@Data
public class MemberDto {

    private Long memberId;

    private String name;

    private String city;

    private String street;

    private String zipcode;

    @Builder
    public MemberDto(Long memberId, String name, String city, String street, String zipcode) {
        this.memberId = memberId;
        this.name = name;
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }

    public Member toEntity(){
        return Member
                .builder()
                .id(this.memberId)
                .name(this.name)
                .address(Address
                        .builder()
                        .city(this.city)
                        .street(this.street)
                        .zipcode(this.zipcode)
                        .build())
                .build();
    }
}
