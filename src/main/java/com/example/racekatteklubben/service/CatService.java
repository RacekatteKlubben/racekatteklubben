package com.example.racekatteklubben.service;

import com.example.racekatteklubben.domain.Cat;
import com.example.racekatteklubben.infrastructur.CatRepository;
import com.example.racekatteklubben.infrastructur.ICatRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatService {
    private final ICatRepository iCatRepository;

    public CatService(ICatRepository iCatRepository) {
        this.iCatRepository = iCatRepository;
    }

    public void createCat(Cat cat, int memberId){
        System.out.println("memberId used when saving cat = " + memberId);
        iCatRepository.createCat(cat, memberId);

    }

    public List<Cat> findAllCats(){
        return iCatRepository.findAllCats();
    }

    public List<Cat> findCatsByMemberId(int memberId) {
        return iCatRepository.findCatsByMemberId(memberId);
    }

}
