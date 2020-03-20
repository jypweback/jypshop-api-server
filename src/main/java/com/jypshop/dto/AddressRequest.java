package com.jypshop.dto;

import com.jypshop.domain.Address;
import lombok.Builder;
import lombok.Data;

/**
 * Created by qkrwpdud1@gmail.com on 2020/03/18
 * Github : http://github.com/jypweback
 * Description :
 */

@Data
public class AddressRequest {

    private String city;
    private String street;
    private String zipcode;

    @Builder
    public AddressRequest(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }

    public Address toEntity(){
        return Address.builder()
                .city(city)
                .street(street)
                .zipcode(zipcode)
                .build();
    }


}
