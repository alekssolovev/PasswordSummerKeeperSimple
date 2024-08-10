package org.springproject.passwordsummerkeepersimple.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springproject.passwordsummerkeepersimple.entity.UserAccount;
import org.springproject.passwordsummerkeepersimple.entity.UserAccountKeys;

import javax.crypto.SecretKey;
import java.security.MessageDigest;

@Service
public class AccountValidationService {

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private UserAccountKeyService userAccountKeyService;

    @Autowired
    private EncryptionService encryptionService;

    @Autowired
    private HashingService hashingService;


    public boolean verifyPassword(String inputPassword, String storedHashedPasswordBase64, String storedSaltBase64) throws Exception {
        // Decode stored salt and hashed password from Base64
        byte[] storedSalt = hashingService.decodeBase64(storedSaltBase64);
        byte[] storedHashedPassword = hashingService.decodeBase64(storedHashedPasswordBase64);

        // Generate hash of input password using stored salt
        byte[] inputHashedPassword = hashingService.generateHash(inputPassword, storedSalt);

        // Compare generated hash with stored hashed password
        return MessageDigest.isEqual(storedHashedPassword, inputHashedPassword);
    }

    public boolean validateUserCredentials(String username, String password) throws Exception {
        SecretKey secretKey = null;
        String storedPassword = null;

        // Iterate through all user account keys to find the matching secret key for the username
        for (UserAccountKeys j : userAccountKeyService.getAllUserAccountKeys()){
            if (username.equals(j.getUsername())){
                secretKey = j.getSecretKey();
                break;
            }
        }

        // Iterate through all user info to find the matching username and validate password
        for (UserAccount i : userAccountService.getAllUserInfo()){
            if (username.equals(i.getUsername()) && secretKey != null){
                // Decrypt stored password using secret key
                storedPassword = encryptionService.decryptData(i.getPassword(), secretKey);
            }

            // Verify the password using stored hashed password and salt
            if (username.equals(i.getUsername()) && verifyPassword(password, storedPassword, i.getSaltBase64())){
                return true; // Credentials are valid
            }
        }
        return false; // Credentials are invalid
    }
}