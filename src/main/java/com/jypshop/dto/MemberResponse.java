package com.jypshop.dto;

import com.jypshop.domain.Address;
import com.jypshop.domain.Member;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qkrwpdud1@gmail.com on 2020/03/19
 * Github : http://github.com/jypweback
 * Description :
 */

@Data
public class MemberResponse {

    private Long memberId;
    private String name;
    private AddressResponse addressResponse;

    @Builder
    public MemberResponse(Long memberId, String name, AddressResponse addressResponse) {
        this.memberId = memberId;
        this.name = name;
        this.addressResponse = addressResponse;
    }
}
