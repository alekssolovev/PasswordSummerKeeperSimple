package org.springproject.passwordsummerkeepersimple.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springproject.passwordsummerkeepersimple.entity.PasswordInfoKeys;
import org.springproject.passwordsummerkeepersimple.repositiry.PasswordInfoKeyRepo;

import java.util.List;

@Service
public class PasswordInfoKeyService {

    @Autowired
    PasswordInfoKeyRepo passwordInfoKeyRepo;

    public PasswordInfoKeys savePasswordInfoKeys(PasswordInfoKeys passwordInfoKeys) {
        return passwordInfoKeyRepo.save(passwordInfoKeys); // Save the password keys using the repository to the database
    }

    public List<PasswordInfoKeys> getAllPasswordInfoKeys() {
        return passwordInfoKeyRepo.findAll(); // Retrieve all password keys using the repository from the database
    }
    public PasswordInfoKeys getPasswordInfoKeybyid(int id) {
        return passwordInfoKeyRepo.findById(id).orElse(null); // Retrieve passwordkey related to the id
    }
    public void deletePasswordInfoKey(int id) {
        passwordInfoKeyRepo.deleteById(id);
    }
}