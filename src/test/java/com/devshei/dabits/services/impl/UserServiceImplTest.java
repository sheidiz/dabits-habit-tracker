package com.devshei.dabits.services.impl;

import com.devshei.dabits.domain.UserEntity;
import com.devshei.dabits.dto.User;
import com.devshei.dabits.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.devshei.dabits.TestData.testUser;
import static com.devshei.dabits.TestData.testUserEntity;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl underTest;

    @Test
    public void testThatUserIsSaved() {
        final User user = testUser();
        final UserEntity userEntity = testUserEntity();

        when(userRepository.save(eq(userEntity))).thenReturn(userEntity);

        final User result = underTest.save(user);
        assertEquals(user, result);
    }

    @Test
    public void testThatFindByIdReturnsEmptyWhenNoUser() {
        final Long id = 1L;

        when(userRepository.findById(id)).thenReturn(Optional.empty());

        final Optional<User> result = underTest.findById(id);
        assertEquals(Optional.empty(), result);
    }

    @Test
    public void testThatFindByIdReturnsUserWhenExists() {
        final User user = testUser();
        final UserEntity userEntity = testUserEntity();

        when(userRepository.findById(eq(user.getId()))).thenReturn(Optional.of((userEntity)));

        final Optional<User> result = underTest.findById(user.getId());
        assertEquals(Optional.of(user), result);
    }

    @Test
    public void testListUsersReturnsEmptyListWhenNoUsersExist() {
        when(userRepository.findAll()).thenReturn(new ArrayList<>());

        final List<User> result = underTest.listUsers();
        assertEquals(0, result.size());
    }
    @Test
    public void testListUsersReturnsUsersWhenExist() {
        final UserEntity userEntity = testUserEntity();
        when(userRepository.findAll()).thenReturn(List.of(userEntity));

        final List<User> result = underTest.listUsers();
        assertEquals(1, result.size());
    }

    @Test
    public void testUserExistsReturnsFalseWhenUserDoesntExists() {
        when(userRepository.existsById(any())).thenReturn(false);

        final boolean result = underTest.isUserExists(testUser());
        assertFalse(result);
    }
    @Test
    public void testUserExistsReturnsTrueWhenUserDoesExists() {
        when(userRepository.existsById(any())).thenReturn(true);

        final boolean result = underTest.isUserExists(testUser());
        assertTrue(result);
    }
    @Test
    public void testDeleteCategoryDeletesCategory() {
        final Long id = 1L;
        underTest.deleteUserById(id);
        verify(userRepository, times(1)).deleteById(eq(id));
    }

}
