package com.service.aichi.mapper.interfaces;

import com.service.aichi.dto.RegisterDto;
import com.service.aichi.dto.UserDto;
import com.service.aichi.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-26T08:39:14+0700",
    comments = "version: 1.6.0.Beta1, compiler: javac, environment: Java 17.0.9 (Oracle Corporation)"
)
@Component
public class IUserMapperImpl implements IUserMapper {

    @Override
    public User userDtoToUser(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.firstName( userDto.getFirstName() );
        user.lastName( userDto.getLastName() );
        user.birthDate( userDto.getBirthDate() );
        user.gender( userDto.getGender() );
        user.address( userDto.getAddress() );
        user.avatar( userDto.getAvatar() );
        user.facebook( userDto.getFacebook() );
        user.phoneNumber( userDto.getPhoneNumber() );
        user.email( userDto.getEmail() );

        return user.build();
    }

    @Override
    public UserDto userToUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto.UserDtoBuilder userDto = UserDto.builder();

        userDto.firstName( user.getFirstName() );
        userDto.lastName( user.getLastName() );
        userDto.birthDate( user.getBirthDate() );
        userDto.gender( user.getGender() );
        userDto.phoneNumber( user.getPhoneNumber() );
        userDto.address( user.getAddress() );
        userDto.avatar( user.getAvatar() );
        userDto.facebook( user.getFacebook() );
        userDto.email( user.getEmail() );

        return userDto.build();
    }

    @Override
    public User registerDtoToUser(RegisterDto registerDto) {
        if ( registerDto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.firstName( registerDto.getFirstName() );
        user.lastName( registerDto.getLastName() );
        user.email( registerDto.getEmail() );
        user.username( registerDto.getUsername() );
        user.password( registerDto.getPassword() );

        return user.build();
    }

    @Override
    public RegisterDto userToRegisterDto(User user) {
        if ( user == null ) {
            return null;
        }

        RegisterDto.RegisterDtoBuilder registerDto = RegisterDto.builder();

        registerDto.firstName( user.getFirstName() );
        registerDto.lastName( user.getLastName() );
        registerDto.email( user.getEmail() );
        registerDto.username( user.getUsername() );
        registerDto.password( user.getPassword() );

        return registerDto.build();
    }
}
