package com.voyager.phonebook.service;

import com.voyager.phonebook.model.User;
import com.voyager.phonebook.repository.RoleRepository;
import com.voyager.phonebook.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.MockitoAnnotations.initMocks;

public class UserServiceTest {

    @Mock
    private UserRepository mockUserRepository;
    @Mock
    private RoleRepository mockRoleRepository;
    @Mock
    private BCryptPasswordEncoder mockBCryptPasswordEncoder;

    private UserService userServiceUnderTest;
    private User user;

    @Before
    public void setUp() {
        initMocks(this);
        userServiceUnderTest = new UserService(mockUserRepository,
                                               mockRoleRepository,
                                               mockBCryptPasswordEncoder);
        user = User.builder()
                .id(1)
                .name("Ievgen")
                .lastName("Gusar")
                .login("voyager")
                .build();

        Mockito.when(mockUserRepository.save(any()))
            .thenReturn(user);
        Mockito.when(mockUserRepository.findByLogin(anyString()))
            .thenReturn(user);
    }

    @Test
    public void testFindUserByEmail() {
        final String email = "test@test.com";

        final User result = userServiceUnderTest.findUserByLogin(email);

        assertEquals(email, result.getLogin());
    }

    @Test
    public void testSaveUser() {
        final String email = "test@test.com";

        User result = userServiceUnderTest.saveUser(User.builder().build());

        assertEquals(email, result.getLogin());
    }
}