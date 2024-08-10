package org.springproject.passwordsummerkeepersimple.repositiry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springproject.passwordsummerkeepersimple.entity.UserAccountKeys;

public interface UserAccountKeyRepo extends JpaRepository<UserAccountKeys, Integer> {

}