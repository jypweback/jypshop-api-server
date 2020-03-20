package com.jypshop.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by qkrwpdud1@gmail.com on 2020/03/20
 * Github : http://github.com/jypweback
 * Description :
 */

@Data
public class OrderSearchFilter {

    private Long itemId;

    private String itemName;

    private String memberName;

    private LocalDateTime orderDate;

}
