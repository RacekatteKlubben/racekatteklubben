package com.example.racekatteklubben.service;

import com.example.racekatteklubben.domain.Cat;
import com.example.racekatteklubben.infrastructur.CatRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatService {

    private final CatRepository catRepository;

    public CatService(CatRepository catRepository) {
        this.catRepository = catRepository;
    }

    public void createCat(Cat cat){
        catRepository.createCat(cat);
    }

    public List<Cat> findAllCats(){
        return catRepository.findAllCats();
    }
}
