package com.example.racekatteklubben.infrastructur;

import com.example.racekatteklubben.domain.CatShow;

import java.util.List;

public interface ICatShowRepository {
    void createCatShow(CatShow catShow);
    List<CatShow> findAllCatShows();
    CatShow findCatShowById(int catShowId);
}