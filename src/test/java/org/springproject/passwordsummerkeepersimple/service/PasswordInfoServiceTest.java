package org.springproject.passwordsummerkeepersimple.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springproject.passwordsummerkeepersimple.entity.PasswordInfo;
import org.springproject.passwordsummerkeepersimple.entity.PasswordInfoKeys;
import org.springproject.passwordsummerkeepersimple.repositiry.PasswordInfoKeyRepo;
import org.springproject.passwordsummerkeepersimple.repositiry.PasswordInfoRepo;

import javax.crypto.SecretKey;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

public class PasswordInfoServiceTest {

    @Mock
    private PasswordInfoRepo passwordInfoRepo;

    @InjectMocks
    private PasswordInfoService passwordService;

    @Mock
    private PasswordInfoKeyRepo passwordInfoKeyRepo;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSavePasswordInfo() {
        // Исходные данные для сохранения
        PasswordInfo passwordInfo = new PasswordInfo("user1", "owner1", "encryptedPassword");

        // Мокирование поведения репозитория при сохранении
        when(passwordInfoRepo.save(passwordInfo)).thenReturn(passwordInfo);

        // Вызов тестируемого метода
        PasswordInfo result = passwordService.savePasswordInfo(passwordInfo);

        // Проверка, что метод save был вызван с правильным аргументом
        verify(passwordInfoRepo, times(1)).save(passwordInfo);

        // Проверка, что возвращаемый результат соответствует ожидаемому
        assertEquals(passwordInfo, result);
    }

    @Test
    public void testGetPasswordInfoById_Found() {
        // Исходные данные для теста
        int id = 1;
        PasswordInfo passwordInfo = new PasswordInfo("user1", "owner1", "encryptedPassword");

        // Мокирование поведения репозитория при вызове findById
        when(passwordInfoRepo.findById(id)).thenReturn(Optional.of(passwordInfo));

        // Вызов тестируемого метода
        PasswordInfo result = passwordService.getPasswordInfobyid(id);

        // Проверка, что возвращаемый объект совпадает с ожидаемым
        assertEquals(passwordInfo, result);
    }

    @Test
    public void testGetPasswordInfoById_NotFound() {
        // Исходные данные для теста
        int id = 1;

        // Мокирование поведения репозитория при вызове findById (возвращаем Optional.empty())
        when(passwordInfoRepo.findById(id)).thenReturn(Optional.empty());

        // Вызов тестируемого метода
        PasswordInfo result = passwordService.getPasswordInfobyid(id);

        // Проверка, что возвращаемое значение null, так как объект не найден
        assertNull(result);
    }

}
