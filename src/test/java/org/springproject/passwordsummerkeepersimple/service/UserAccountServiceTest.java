package org.springproject.passwordsummerkeepersimple.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springproject.passwordsummerkeepersimple.entity.UserAccountKeys;
import org.springproject.passwordsummerkeepersimple.repositiry.UserAccountKeyRepo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import javax.crypto.SecretKey;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class UserAccountServiceTest {

    @Mock
    private UserAccountKeyRepo userAccountKeyRepo;

    @Mock
    private SecretKey secretKey;

    @InjectMocks
    private UserAccountKeyService userAccountService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveUserAccountKeys() {
        // Исходные данные для теста
        UserAccountKeys userAccountKeys = new UserAccountKeys("user1", secretKey);

        // Мокирование поведения репозитория при сохранении
        when(userAccountKeyRepo.save(userAccountKeys)).thenReturn(userAccountKeys);

        // Вызов тестируемого метода
        UserAccountKeys result = userAccountService.saveUserAccountKeys(userAccountKeys);

        // Проверка, что метод save был вызван с правильным аргументом
        verify(userAccountKeyRepo, times(1)).save(userAccountKeys);

        // Проверка, что возвращаемый результат соответствует ожидаемому
        assertEquals(userAccountKeys, result);
    }

    @Test
    public void testGetAllUserAccountKeys_WithData() {
        // Исходные данные для теста
        UserAccountKeys userAccountKey1 = new UserAccountKeys("user1", secretKey);
        UserAccountKeys userAccountKey2 = new UserAccountKeys("user2", secretKey);

        List<UserAccountKeys> userAccountKeysList = Arrays.asList(userAccountKey1, userAccountKey2);

        // Мокирование поведения репозитория при вызове findAll
        when(userAccountKeyRepo.findAll()).thenReturn(userAccountKeysList);

        // Вызов тестируемого метода
        List<UserAccountKeys> result = userAccountService.getAllUserAccountKeys();

        // Проверка, что возвращаемый результат соответствует ожидаемому списку
        assertEquals(userAccountKeysList, result);
    }

    @Test
    public void testGetAllUserAccountKeys_NoData() {
        // Пустой список как результат findAll
        when(userAccountKeyRepo.findAll()).thenReturn(Collections.emptyList());

        // Вызов тестируемого метода
        List<UserAccountKeys> result = userAccountService.getAllUserAccountKeys();

        // Проверка, что возвращаемый список пуст
        assertTrue(result.isEmpty());
    }
}
