package com.jypshop.repository;

import com.jypshop.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by qkrwpdud1@gmail.com on 2020/03/09
 * Github : http://github.com/jypweback
 * Description :
 */
public interface ItemRepository extends JpaRepository<Item, Long> {
}
