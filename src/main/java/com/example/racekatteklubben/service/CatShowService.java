package com.example.racekatteklubben.service;

import com.example.racekatteklubben.infrastructur.ICatShowRepository;
import org.springframework.stereotype.Service;

@Service
public class CatShowService {
    private final ICatShowRepository iCatShowRepository;

    public CatShowService(ICatShowRepository iCatShowRepository) {
        this.iCatShowRepository = iCatShowRepository;
    }
}
