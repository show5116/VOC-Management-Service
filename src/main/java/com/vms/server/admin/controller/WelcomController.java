package com.vms.server.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
@RequiredArgsConstructor
public class WelcomController {



    private final MessageSource messageSource;

    @GetMapping("/welcome")
    public String welcome(Locale locale) {
        System.out.println(messageSource.getMessage("welcome.message", null, locale));
        return messageSource.getMessage("welcome.message", null, locale);
    }
}
