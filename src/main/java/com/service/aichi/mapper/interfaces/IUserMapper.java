package com.service.aichi.mapper.interfaces;

import com.service.aichi.dto.RegisterDto;
import com.service.aichi.dto.UserDto;
import com.service.aichi.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface IUserMapper {

    IUserMapper INSTANCE = Mappers.getMapper(IUserMapper.class);

    public User userDtoToUser(UserDto userDto);

    public UserDto userToUserDto(User user);

    public User registerDtoToUser(RegisterDto registerDto);

    public RegisterDto userToRegisterDto(User user);

}
