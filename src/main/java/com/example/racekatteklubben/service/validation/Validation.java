package com.example.racekatteklubben.service.validation;

import com.example.racekatteklubben.domain.Cat;
import com.example.racekatteklubben.domain.Member;
import com.example.racekatteklubben.domain.Race;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Validation {
    public void validateMember(Member member) {
        int id = member.getMemberId();
        String name = member.getName();
        String email = member.getEmail();
        String password = member.getPassword();
        String phoneNumber = member.getPhoneNumber();

        if (id < 0) {
            throw new ValidationExceptionMember("Id kan ikke tilgå negative værdier");
        }

        if (name == null || name.isEmpty()) {
            throw new ValidationExceptionMember("Brugernavn kan ikke være tomt eller null");
        }

        if (name.length() < 3) {
            throw new ValidationExceptionMember("Brugernavn skal være mindst 3 tegn langt");
        }

        if (email == null || email.isEmpty()) {
            throw new ValidationExceptionMember("Email kan ikke være tomt eller null");
        }

        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new ValidationExceptionMember("Email format er ugyldigt");
        }

        if (password == null || password.isEmpty()) {
            throw new ValidationExceptionMember("Password skal udfyldes, må ikke være tomt eller null");
        }

        if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")) {
            throw new ValidationExceptionMember( "Password skal være mindst 8 tegn og indeholde stort bogstav, lille bogstav, tal og specialtegn");
        }

        if (phoneNumber == null || phoneNumber.isEmpty()) {
            throw new ValidationExceptionMember("Telefonnummer kan ikke være tomt eller null");
        }

        if (!phoneNumber.matches("\\d{8}")) {
            throw new ValidationExceptionMember("Telefonnummer skal være præcis 8 cifre");
        }
    }

    public void validateMemberLogin(Member member) {
        String email = member.getEmail();
        String password = member.getPassword();

        if (email == null || email.isEmpty()) {
            throw new ValidationExceptionMember("Email kan ikke være tomt eller null");
        }

        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new ValidationExceptionMember("Email format er ugyldigt");
        }

        if (password == null || password.isEmpty()) {
            throw new ValidationExceptionMember("Password kan ikke være tomt");
        }
    }

    public void validateCat(Cat cat){
        int id = cat.getCatId();
        String name = cat.getName();
        String mom = cat.getMom();
        String dad = cat.getDad();
        String color = cat.getColor();
        Race race = cat.getRace();
        int memberId = cat.getMemberId();

        if (id < 0) {
            throw new ValidationExceptionCat("Kat id kan ikke være negativt");
        }

        if (name == null || name.isEmpty()) {
            throw new ValidationExceptionCat("Kat navn ikke være tomt eller null");
        }

        if (name.length() < 3) {
            throw new ValidationExceptionCat("Kat navn skal mindst være 3 bogstaver langt");
        }

        if (!name.matches("[a-zA-ZæøåÆØÅ ]+")) {
            throw new ValidationExceptionCat("Kat navn må kun indeholde bogstaver");
        }

        //Katten må gerne have ukendte forældre. Derfor tjekkes der om de ikke er null først, og derved eksistere.
        if (mom != null && !mom.isEmpty()) {
            if (!mom.matches("[a-zA-ZæøåÆØÅ ]+")) {
                throw new ValidationExceptionCat("Morens navn må kun indeholde bogstaver");
            }
        }
        if (dad != null && !dad.isEmpty()) {
            if (!dad.matches("[a-zA-ZæøåÆØÅ ]+")) {
                throw new ValidationExceptionCat("Farens navn må kun indeholde bogstaver");
            }
        }

        if (color == null || color.isEmpty()) {
            throw new ValidationExceptionCat("Farve kan ikke være tom eller null");
        }

        if (!color.matches("[a-zA-ZæøåÆØÅ ]+")) {
            throw new ValidationExceptionCat("Farve må kun indeholde bogstaver");
        }

        if (race == null) {
            throw new ValidationExceptionCat("Race skal vælges");
        }

        if (memberId < 0) {
            throw new ValidationExceptionCat("Katten skal være tilknyttet et gyldigt medlem");
        }
    }
}
