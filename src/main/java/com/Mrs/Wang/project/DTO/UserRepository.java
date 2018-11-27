package com.Mrs.Wang.project.DTO;

import com.Mrs.Wang.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @program: spring-boot-api-project-seed
 * @description:
 * @author: hezijian6338
 * @create: 2018-11-27 10:21
 **/
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
