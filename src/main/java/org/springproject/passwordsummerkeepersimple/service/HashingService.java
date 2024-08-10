package org.springproject.passwordsummerkeepersimple.service;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springproject.passwordsummerkeepersimple.entity.PasswordInfo;
import org.springproject.passwordsummerkeepersimple.entity.PasswordInfoKeys;
import org.springproject.passwordsummerkeepersimple.entity.UserAccount;
import org.springproject.passwordsummerkeepersimple.entity.UserAccountKeys;

import javax.crypto.SecretKey;

@Service
public class HashingService {

    @Autowired
    private EncryptionService encryptionService;

    @Autowired
    private UserAccountKeyService userAccountKeyService;

    @Autowired
    private PasswordInfoKeyService passwordInfoKeyService;

    public String encodeBase64(byte[] base64) {
        return Base64.getEncoder().encodeToString(base64);
    }

    public byte[] decodeBase64(String base64) {
        return Base64.getDecoder().decode(base64);
    }

    public byte[] generateRandomSalt() {
        // Generate a random salt
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);

        return salt;
    }

    public byte[] generateHash(String data , byte[] salt) throws Exception{
        // Hash the data with SHA-256 and the provided salt
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(salt);

        return md.digest(data.getBytes());
    }

    public UserAccount generateSaltHashedPassword(UserAccount userAccount) throws Exception {
        // Generate random salt and hash the password
        byte[] salt = generateRandomSalt();
        byte[] hashedPassword = generateHash(userAccount.getPassword(), salt);

        // Encode salt and hashedPassword to Base64
        String saltBase64 = encodeBase64(salt);
        String hashedPasswordBase64 = encodeBase64(hashedPassword);

        // Generate a secret key for encryption
        SecretKey secretKey = encryptionService.generateSecretKey();

        // Save the secret key associated with the user
        userAccountKeyService.saveUserAccountKeys(new UserAccountKeys(userAccount.getUsername(), secretKey));

        // Update UserInfo with salt and encrypted hashed password
        userAccount.setSaltBase64(saltBase64);
        userAccount.setPassword(encryptionService.encryptData(hashedPasswordBase64, secretKey));

        return userAccount;
    }
    // for passwordinfo
    public PasswordInfo generateEncryptedPassword(PasswordInfo passwordInfo) throws Exception {
        // Generate a secret key for encryption
        SecretKey secretKey = encryptionService.generateSecretKey();

        // Save the secret key associated with the user
        passwordInfoKeyService.savePasswordInfoKeys(new PasswordInfoKeys(passwordInfo.getName(), passwordInfo.getPasswordOwner(), secretKey));

        // Update PasswordInfo with encrypted password
        passwordInfo.setPassword(encryptionService.encryptData(passwordInfo.getPassword(), secretKey));

        return passwordInfo;
    }

    public PasswordInfo updatePassword(PasswordInfo passwordInfo, PasswordInfoKeys passwordInfoKeys) throws Exception {

        // Generate a secret key for encryption
        SecretKey secretKey = encryptionService.generateSecretKey();

        // Update the new secret key associated with the user
        passwordInfoKeys.setSecretKey(secretKey);

        // Update PasswordInfo with encrypted password
//        passwordInfo.setSaltBase64(saltBase64);
        passwordInfo.setPassword(encryptionService.encryptData(passwordInfo.getPassword(), secretKey));

        return passwordInfo;
    }
}