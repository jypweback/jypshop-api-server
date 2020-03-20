package com.jypshop.dto;

import com.jypshop.domain.Address;
import lombok.Builder;
import lombok.Data;

/**
 * Created by qkrwpdud1@gmail.com on 2020/03/19
 * Github : http://github.com/jypweback
 * Description :
 */

@Data
public class AddressResponse {

    private String city;
    private String street;
    private String zipcode;

    @Builder
    public AddressResponse(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
