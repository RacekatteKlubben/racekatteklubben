package com.example.racekatteklubben.service;
import com.example.racekatteklubben.domain.CatShow;
import com.example.racekatteklubben.infrastructur.ICatShowRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatShowService {

    private final ICatShowRepository iCatShowRepository;

    public CatShowService(ICatShowRepository iCatShowRepository) {
        this.iCatShowRepository = iCatShowRepository;
    }

    public void createCatShow(CatShow catShow) {
        iCatShowRepository.createCatShow(catShow);
    }

    public List<CatShow> findAllCatShows() {
        return iCatShowRepository.findAllCatShows();
    }

    public CatShow findCatShowById(int catShowId) {
        return iCatShowRepository.findCatShowById(catShowId);
    }
}