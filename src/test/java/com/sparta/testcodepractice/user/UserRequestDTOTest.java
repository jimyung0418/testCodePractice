package com.sparta.testcodepractice.user;

import com.sparta.testcodepractice.test.CommonTest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.*;

class UserRequestDTOTest implements CommonTest {

    @DisplayName("유저 요청 DTO 생성")
    @Nested
    class createUserRequestDTO {
        @DisplayName("유저 요청 DTO 생성 성공")
        @Test
        void createUserRequestDTO_success() {
            // given
            UserRequestDTO userRequestDTO = new UserRequestDTO();
            userRequestDTO.setUsername(TEST_USER_NAME);
            userRequestDTO.setPassword(TEST_USER_PASSWORD);

            // when
            Set<ConstraintViolation<UserRequestDTO>> violations = validate(userRequestDTO);

            // then
            assertThat(violations).isEmpty();
        }

        @DisplayName("유저 요청 DTO 생성 실패 - 잘못된 username")
        @Test
        void createUserRequestDTO_fail_wrongUserName() {
            // given
            UserRequestDTO userRequestDTO = new UserRequestDTO();
            userRequestDTO.setUsername("Invalid user name"); // Invalid username pattern
            userRequestDTO.setPassword(TEST_USER_PASSWORD);     // Invalid password pattern

            // when
            Set<ConstraintViolation<UserRequestDTO>> violations = validate(userRequestDTO);

            // then
            assertThat(violations).hasSize(1);
            assertThat(violations)
                    .extracting("message")
                    .contains("\"^[a-z0-9]{4,10}$\"와 일치해야 합니다");
        }

        @DisplayName("유저 요청 DTO 생성 실패 - 잘못된 password")
        @Test
        void createUserRequestDTO_wrongPassword() {
            UserRequestDTO userRequestDTO = new UserRequestDTO();
            userRequestDTO.setUsername(TEST_USER_NAME); // Invalid username pattern
            userRequestDTO.setPassword("Invalid password");     // Invalid password pattern

            // when
            Set<ConstraintViolation<UserRequestDTO>> violations = validate(userRequestDTO);

            // then
            assertThat(violations).hasSize(1);
            assertThat(violations)
                    .extracting("message")
                    .contains("\"^[a-zA-Z0-9]{8,15}$\"와 일치해야 합니다");
        }
    }

    private Set<ConstraintViolation<UserRequestDTO>> validate(UserRequestDTO userRequestDTO) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        return validator.validate(userRequestDTO);
    }


}