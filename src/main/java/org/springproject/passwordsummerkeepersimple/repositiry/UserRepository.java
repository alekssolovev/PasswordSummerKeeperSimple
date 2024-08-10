package org.springproject.passwordsummerkeepersimple.repositiry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springproject.passwordsummerkeepersimple.entity.UserAccount;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserAccount, Long> {
    Optional<UserAccount> findByUsername(String username);
}