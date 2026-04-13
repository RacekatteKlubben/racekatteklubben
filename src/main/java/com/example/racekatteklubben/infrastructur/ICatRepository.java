package com.example.racekatteklubben.infrastructur;

import com.example.racekatteklubben.domain.Cat;

import java.util.List;

public interface ICatRepository {
    List<Cat> findAllCats();
    void createCat(Cat cat, int memberId);
    List<Cat> findCatsByMemberId(int memberId);
}
