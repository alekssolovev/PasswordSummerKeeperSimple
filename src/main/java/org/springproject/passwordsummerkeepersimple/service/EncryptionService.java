package org.springproject.passwordsummerkeepersimple.service;

import javax.crypto.*;
import javax.crypto.KeyGenerator;
import java.util.Base64;
import org.springframework.stereotype.Service;

@Service
public class EncryptionService {

    private static final String ALGORITHM = "AES"; // Algorithm used for encryption and decryption

    public SecretKey generateSecretKey() throws Exception {
        // Create a KeyGenerator for AES
        KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITHM);
        return keyGen.generateKey(); // Generate and return a new AES SecretKey
    }

    public String encryptData(String data, SecretKey secretKey) throws Exception {
        // Initialize AES Cipher for encryption
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        // Encrypt the data
        byte[] encryptedData = cipher.doFinal(data.getBytes());

        // Encode encrypted data to Base64
        return Base64.getEncoder().encodeToString(encryptedData);
    }

    public String decryptData(String encryptedData, SecretKey secretKey) throws Exception {
        // Convert encryptedData from Base64 to byte array
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedData);

        // Initialize AES Cipher for decryption
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        // Decrypt the data
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

        // Convert decrypted byte array to String (if applicable)
        return new String(decryptedBytes);
    }
}