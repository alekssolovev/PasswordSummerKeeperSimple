package org.springproject.passwordsummerkeepersimple.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springproject.passwordsummerkeepersimple.entity.UserAccount;
import org.springproject.passwordsummerkeepersimple.repositiry.UserAccountRepo;

import java.util.List;

@Service
public class UserAccountService {

    @Autowired
    private UserAccountRepo userAccountRepo;

    public UserAccount saveUserInfo(UserAccount userAccount){
        return userAccountRepo.save(userAccount); // Save the user information using the repository to the database
    }

    public List<UserAccount> getAllUserInfo(){
        return userAccountRepo.findAll(); // Retrieve all user information using the repository from the database
    }
}