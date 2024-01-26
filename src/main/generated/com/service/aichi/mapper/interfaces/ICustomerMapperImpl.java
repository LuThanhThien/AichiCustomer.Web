package com.service.aichi.mapper.interfaces;

import com.service.aichi.dto.CustomerDto;
import com.service.aichi.entity.Customer;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-26T08:39:14+0700",
    comments = "version: 1.6.0.Beta1, compiler: javac, environment: Java 17.0.9 (Oracle Corporation)"
)
@Component
public class ICustomerMapperImpl implements ICustomerMapper {

    @Override
    public Customer customerDtoToCustomer(CustomerDto customerDto) {
        if ( customerDto == null ) {
            return null;
        }

        Customer.CustomerBuilder customer = Customer.builder();

        return customer.build();
    }

    @Override
    public CustomerDto customerToCustomerDto(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerDto.CustomerDtoBuilder customerDto = CustomerDto.builder();

        return customerDto.build();
    }
}
