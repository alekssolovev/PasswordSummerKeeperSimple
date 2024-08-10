package org.springproject.passwordsummerkeepersimple.repositiry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springproject.passwordsummerkeepersimple.entity.PasswordInfoKeys;

public interface PasswordInfoKeyRepo extends JpaRepository<PasswordInfoKeys, Integer> {
}