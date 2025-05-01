package com.kamilc.universitysystem.domain.dao;

import com.kamilc.universitysystem.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("Should find user by email")
    void shouldFindUserByEmail() {
        // Arrange
        User user = new User();
        user.setEmail("john.doe@example.com");
        user.setName("John");
        user.setSurName("Doe");
        user.setPassword("password123");
        user.setRole(User.UserRole.STUDENT);

        userRepository.save(user);

        // Act
        Optional<User> result = userRepository.findByEmail("john.doe@example.com");

        // Assert
        assertThat(result).isPresent();
        assertThat(result.get().getName()).isEqualTo("John");
        assertThat(result.get().getSurName()).isEqualTo("Doe");
    }
}