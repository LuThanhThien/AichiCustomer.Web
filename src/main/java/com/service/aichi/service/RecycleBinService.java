package com.service.aichi.service;

import com.service.aichi.repository.interfaces.IRecycleBinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RecycleBinService {
    private final IRecycleBinRepository recycleBinRepository;
}
