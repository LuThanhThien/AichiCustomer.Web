package com.service.aichi.mapper.interfaces;

import com.service.aichi.dto.RegisterDto;
import com.service.aichi.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IUserMapperTest {


    @Test
    void userDtoToUser() {
        // given
        User user = new User();
        user.setFirstName("Java");
        user.setLastName("Spring");
        user.setEmail("springjava@gmail.com");
        user.setUsername("springjava@aichi.jp");
        user.setPassword("1234qwer");

        // when
        RegisterDto registerDto = IUserMapper.INSTANCE.userToRegisterDto(user);


        // then
        assertEquals(registerDto.getUsername(), "springjava@aichi.jp");
        assertEquals(registerDto.getEmail(), "springjava@gmail.com");
        assertEquals(registerDto.getFirstName(), "Java");
        assertEquals(registerDto.getLastName(), "Spring");
        assertEquals(registerDto.getPassword(), "1234qwer");
    }

    @Test
    void userToUserDto() {
    }

    @Test
    void registerDtoToUser() {
        RegisterDto registerDto = new RegisterDto();
        registerDto.setFirstName("Java");
        registerDto.setLastName("Spring");
        registerDto.setEmail("springjava@gmail.com");
        registerDto.setUsername("springjava@aichi.jp");
        registerDto.setPassword("1234qwer");

        // when
        User user = IUserMapper.INSTANCE.registerDtoToUser(registerDto);


        // then
        assertEquals(user.getUsername(), "springjava@aichi.jp");
        assertEquals(user.getEmail(), "springjava@gmail.com");
        assertEquals(user.getFirstName(), "Java");
        assertEquals(user.getLastName(), "Spring");
        assertEquals(user.getPassword(), "1234qwer");
    }

    @Test
    void userToRegisterDto() {
    }
}