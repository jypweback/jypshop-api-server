package com.jypshop.controller;

import com.jypshop.dto.ItemDto;
import com.jypshop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by qkrwpdud1@gmail.com on 2020/03/10
 * Github : http://github.com/jypweback
 * Description :
 */

@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
public class ItemApiController {

    private final ItemService itemService;

    @PostMapping("/item")
    public ItemDto createItem(@Valid @RequestBody ItemDto itemDto){
        return itemService.createItem(itemDto);
    }


}
