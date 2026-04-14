package com.example.racekatteklubben;

import com.example.racekatteklubben.service.validation.ValidationExceptionCat;
import com.example.racekatteklubben.service.validation.ValidationExceptionMember;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ValidationExceptionMember.class)
    public String handleValidationExceptionMember(ValidationExceptionMember em, Model model) {
        model.addAttribute("message", em.getMessage());
        return "error";
    }

    @ExceptionHandler(ValidationExceptionCat.class)
    public String handleValidationExceptionCat(ValidationExceptionCat em, Model model) {
        model.addAttribute("message", em.getMessage());
        return "error";
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex, Model model) {
        model.addAttribute("message", ex.getMessage());
        return "error";
    }
}
