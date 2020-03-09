package com.jypshop.repository;

import com.jypshop.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by qkrwpdud1@gmail.com on 2020/03/09
 * Github : http://github.com/jypweback
 * Description :
 */

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
