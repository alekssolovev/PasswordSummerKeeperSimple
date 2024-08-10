package org.springproject.passwordsummerkeepersimple.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PasswordGeneratorServiceTest {

    private static final int PASSWORD_LENGTH = 12;
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    @Mock
    private SecureRandom random;

    @InjectMocks
    private PasswordGeneratorService passwordGenerator;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGeneratePassword_Length() {
        // Мокирование случайного значения
        when(random.nextInt(CHARACTERS.length())).thenReturn(0);

        // Вызов тестируемого метода
        String password = passwordGenerator.generatePassword();

        // Проверка, что длина пароля соответствует PASSWORD_LENGTH
        assertEquals(PASSWORD_LENGTH, password.length());
    }

   /* @Test
    public void testGeneratePassword_Characters() {
        // Мокирование случайных значений, чтобы охватить все символы
        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            when(random.nextInt(CHARACTERS.length())).thenReturn(i % CHARACTERS.length());
        }

        // Вызов тестируемого метода
        String password = passwordGenerator.generatePassword();

        // Проверка, что каждый символ пароля входит в CHARACTERS
        for (char c : password.toCharArray()) {
            assertTrue(CHARACTERS.indexOf(c) >= 0);
        }
    }*/

    @Test
    public void testGeneratePassword_Uniqueness() {
        // Сгенерировать несколько паролей
        Set<String> generatedPasswords = new HashSet<>();
        for (int i = 0; i < 1000; i++) {
            String password = passwordGenerator.generatePassword();
            generatedPasswords.add(password);
        }

        // Проверка, что пароли уникальны (или что значительное большинство паролей уникально)
        assertTrue(generatedPasswords.size() > 950); // Проверка на высокую степень уникальности
    }
}
