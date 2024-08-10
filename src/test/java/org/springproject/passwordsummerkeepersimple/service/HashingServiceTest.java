package org.springproject.passwordsummerkeepersimple.service;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;


import javax.crypto.SecretKey;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import org.springproject.passwordsummerkeepersimple.entity.PasswordInfo;
import org.springproject.passwordsummerkeepersimple.entity.PasswordInfoKeys;


public class HashingServiceTest {
    @Mock
    private EncryptionService encryptionService;

    @Mock
    private PasswordInfoKeyService passwordInfoKeyService;

    @InjectMocks
    private HashingService hashingService;

   /* @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }*/

    @Test
    public void testGenerateHash() throws Exception {
        // Исходные данные для хеширования
        String data = "Hello, World!";
        byte[] salt = "randomSalt".getBytes();

        // Создание экземпляра класса, где реализован метод generateHash
         hashingService = new HashingService();

        // Вызов метода generateHash и получение хеша
        byte[] hash1 = hashingService.generateHash(data, salt);

        // Проверка, что хэш не null и не пустой
        assertNotNull(hash1);
        assertTrue(hash1.length > 0);

        // Повторное хеширование тех же данных с той же солью
        byte[] hash2 = hashingService.generateHash(data, salt);

        // Проверка, что хеши одинаковы при одинаковых входных данных и соли
        assertArrayEquals(hash1, hash2);

        // Хеширование тех же данных, но с другой солью
        byte[] newSalt = "differentSalt".getBytes();
        byte[] hash3 = hashingService.generateHash(data, newSalt);

        // Проверка, что хеши различаются при разных солях
        assertFalse(Arrays.equals(hash1, hash3));

        // Хеширование других данных с той же солью
        String newData = "Hello, Universe!";
        byte[] hash4 = hashingService.generateHash(newData, salt);

        // Проверка, что хеши различаются при разных входных данных
        assertFalse(Arrays.equals(hash1, hash4));
    }

    /*@Test
    public void testGenerateEncryptedPassword() throws Exception {
        // Исходные данные
        String plainPassword = "myPlainPassword";
        PasswordInfo passwordInfo = new PasswordInfo("user1", "owner1", plainPassword);

        // Мокируем генерацию секретного ключа
        SecretKey secretKey = mock(SecretKey.class);
        when(encryptionService.generateSecretKey()).thenReturn(secretKey);

        // Мокируем шифрование пароля
        String encryptedPassword = "encryptedPassword";
        when(encryptionService.encryptData(plainPassword, secretKey)).thenReturn(encryptedPassword);

        // Вызов тестируемого метода
         hashingService = new HashingService();
        PasswordInfo result = hashingService.generateEncryptedPassword(passwordInfo);

        // Проверка, что ключ был сохранен с правильными параметрами
        ArgumentCaptor<PasswordInfoKeys> captor = ArgumentCaptor.forClass(PasswordInfoKeys.class);
        verify(passwordInfoKeyService).savePasswordInfoKeys(captor.capture());

        PasswordInfoKeys capturedKey = captor.getValue();
        assertEquals("user1", capturedKey.getName());
        assertEquals("owner1", capturedKey.getPasswordOwner());
        assertEquals(secretKey, capturedKey.getSecretKey());

        // Проверка, что пароль был зашифрован и установлен в PasswordInfo
        assertNotNull(result);
        assertEquals(encryptedPassword, result.getPassword());

        // Проверка, что методы были вызваны один раз
        verify(encryptionService, times(1)).generateSecretKey();
        verify(encryptionService, times(1)).encryptData(plainPassword, secretKey);
        verify(passwordInfoKeyService, times(1)).savePasswordInfoKeys(any(PasswordInfoKeys.class));
    }*/
}
