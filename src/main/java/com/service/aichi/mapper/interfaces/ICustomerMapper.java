package com.service.aichi.mapper.interfaces;

import com.service.aichi.dto.CustomerDto;
import com.service.aichi.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ICustomerMapper {


    ICustomerMapper INSTANCE = Mappers.getMapper(ICustomerMapper.class);

    public Customer customerDtoToCustomer(CustomerDto customerDto);

    public CustomerDto customerToCustomerDto(Customer customer);
}
