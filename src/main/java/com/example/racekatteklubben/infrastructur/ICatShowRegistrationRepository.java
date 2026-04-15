package com.example.racekatteklubben.infrastructur;
import com.example.racekatteklubben.domain.CatShowRegistration;
import java.util.List;

public interface ICatShowRegistrationRepository {
    void createRegistration(CatShowRegistration registration);
    List<CatShowRegistration> findByCatId(int catId);
    List<CatShowRegistration> findByCatShowId(int catShowId);
    void deleteRegistration(int registrationId);
}