package org.springproject.passwordsummerkeepersimple.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;

import static org.junit.jupiter.api.Assertions.*;

public class EncryptionServiceTest {

    private EncryptionService cryptoService;

    @BeforeEach
    public void setUp() {
        cryptoService = new EncryptionService();
    }

    @Test
    public void testGenerateSecretKey() throws Exception {
        // Вызов метода для генерации секретного ключа
        SecretKey secretKey = cryptoService.generateSecretKey();

        // Проверка, что ключ не равен null
        assertNotNull(secretKey);
    }

    @Test
    public void testEncryptData() throws Exception {
        // Исходные данные и ключ
        String data = "Hello, World!";
        SecretKey secretKey = cryptoService.generateSecretKey();

        // Шифрование данных
        String encryptedData = cryptoService.encryptData(data, secretKey);

        // Проверка, что зашифрованные данные не равны исходным данным
        assertNotNull(encryptedData);
        assertNotEquals(data, encryptedData);
    }

    @Test
    public void testDecryptData() throws Exception {
        // Исходные данные и ключ
        String data = "Hello, World!";
        SecretKey secretKey = cryptoService.generateSecretKey();

        // Шифрование данных
        String encryptedData = cryptoService.encryptData(data, secretKey);

        // Расшифрование данных
        String decryptedData = cryptoService.decryptData(encryptedData, secretKey);

        // Проверка, что расшифрованные данные совпадают с исходными данными
        assertEquals(data, decryptedData);
    }
}
