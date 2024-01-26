package com.service.aichi.service;

import com.service.aichi.repository.interfaces.ICustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomerService {
    private final ICustomerRepository customerRepository;

}
