package com.example.racekatteklubben.service;

import com.example.racekatteklubben.domain.Cat;
import com.example.racekatteklubben.infrastructur.CatRepository;
import com.example.racekatteklubben.infrastructur.ICatRepository;
import com.example.racekatteklubben.service.validation.Validation;
import com.example.racekatteklubben.service.validation.ValidationExceptionCat;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatService {
    private final ICatRepository iCatRepository;
    private final Validation validate;

    public CatService(ICatRepository iCatRepository, Validation validate) {
        this.iCatRepository = iCatRepository;
        this.validate = validate;
    }

    public void createCat(Cat cat, int memberId){
        validate.validateCat(cat);
        try {
            iCatRepository.createCat(cat, memberId);
        } catch (Exception e){
            throw new ValidationExceptionCat("Fejl ved oprettelsen af en kat i systemet");
        }
    }

    public List<Cat> findAllCats(){
        return iCatRepository.findAllCats();
    }

    public List<Cat> findCatsByMemberId(int memberId) {
        return iCatRepository.findCatsByMemberId(memberId);
    }
}
