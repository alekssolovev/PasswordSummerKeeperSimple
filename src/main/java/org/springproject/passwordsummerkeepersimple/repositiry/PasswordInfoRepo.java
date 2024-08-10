package org.springproject.passwordsummerkeepersimple.repositiry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springproject.passwordsummerkeepersimple.entity.PasswordInfo;

import java.util.Optional;

public interface PasswordInfoRepo extends JpaRepository<PasswordInfo, Integer> {

}