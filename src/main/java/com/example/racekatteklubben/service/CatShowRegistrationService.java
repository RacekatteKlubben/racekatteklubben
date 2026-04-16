package com.example.racekatteklubben.service;
import com.example.racekatteklubben.domain.Cat;
import com.example.racekatteklubben.domain.CatShowRegistration;
import com.example.racekatteklubben.infrastructur.ICatRepository;
import com.example.racekatteklubben.infrastructur.ICatShowRegistrationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CatShowRegistrationService {

    private final ICatShowRegistrationRepository iCatShowRegistrationRepository;
    private final ICatRepository iCatRepository;

    public CatShowRegistrationService(ICatShowRegistrationRepository iCatShowRegistrationRepository,
                                      ICatRepository iCatRepository) {
        this.iCatShowRegistrationRepository = iCatShowRegistrationRepository;
        this.iCatRepository = iCatRepository;
    }

    public void registerCatToShow(int catId, int catShowId, int memberId) {
        Cat cat = iCatRepository.findById(catId);

        if (cat == null) {
            throw new RuntimeException("Katten findes ikke");
        }

        if (cat.getMemberId() != memberId) {
            throw new RuntimeException("Du kan kun tilmelde dine egne katte");
        }

        CatShowRegistration registration = new CatShowRegistration();
        registration.setCatId(catId);
        registration.setCatShowId(catShowId);
        registration.setRegistrationDate(LocalDateTime.now());
        registration.setStatus("TILMELDT");

        iCatShowRegistrationRepository.createRegistration(registration);
    }

    public List<CatShowRegistration> findRegistrationsByCatId(int catId) {
        return iCatShowRegistrationRepository.findByCatId(catId);
    }

    public void deleteRegistration(int registrationId) {
        iCatShowRegistrationRepository.deleteRegistration(registrationId);
    }
}