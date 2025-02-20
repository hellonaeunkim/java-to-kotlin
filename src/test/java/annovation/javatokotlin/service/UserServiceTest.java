package annovation.javatokotlin.service;

import annovation.javatokotlin.entity.UserEntity;
import annovation.javatokotlin.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository; // 가짜(Mock) 객체 생성

    @InjectMocks
    private UserService userService; // 테스트할 대상

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createUser() {
        // given
        UserEntity userEntity = new UserEntity(1L, "ann", "ann@email.com");
        when(userRepository.save(any())).thenReturn(userEntity);

        // when
        UserEntity actual = userService.createUser("ann", "ann@email.com");

        // then
        assertNotNull(actual);
        assertEquals("ann", actual.getUsername());
        assertEquals("ann@email.com", actual.getEmail());
    }

    @Test
    void getUser() {
        // given
        Long id = 1L;
        UserEntity userEntity = new UserEntity(1L, "ann", "ann@email.com");
        when(userRepository.findById(id)).thenReturn(Optional.of(userEntity));

        // when
        Optional<UserEntity> actual = userService.findById(id);

        // then
        assertNotNull(actual);
        assertEquals("ann", actual.get().getUsername());
        assertEquals("ann@email.com", actual.get().getEmail());
    }

    @Test
    void updateUser() {
        // given
        Long userId = 1L;
        String newUsername = "updatedAnn";
        UserEntity existingUser = new UserEntity(userId, "ann", "ann@email.com");

        // Mock: 기존 사용자 찾기
        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        // Mock: save() 호출 시 변경된 객체 반환
        when(userRepository.save(any(UserEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // when
        UserEntity updatedUser = userService.updateUser(userId, newUsername);

        // then
        assertNotNull(updatedUser);
        assertEquals(newUsername, updatedUser.getUsername());
        assertEquals("ann@email.com", updatedUser.getEmail());
    }

    @Test
    void deleteUser() {
        // given
        Long userId = 1L;
        UserEntity existingUser = new UserEntity(userId, "ann", "ann@email.com");

        // when
        doNothing().when(userRepository).deleteById(userId);

        // then
        assertDoesNotThrow(() -> userService.deleteUser(userId)); // 예외 발생하지 않는지 확인
        verify(userRepository, times(1)).deleteById(userId); // deleteById가 1번 호출되었는지 검증
    }

}