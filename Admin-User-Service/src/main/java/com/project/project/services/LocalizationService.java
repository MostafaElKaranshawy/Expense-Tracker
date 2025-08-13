package com.project.project.services;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class LocalizationService {

    private final MessageSource messageSource;

    public LocalizationService(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /**
     * Get a localized message for a given key.
     */
    public String getMessage(String key, Locale locale) {
        return messageSource.getMessage(key, null, locale);
    }

    /**
     * Get localized category name.
     */
    public String getCategoryName(Enum<?> category, Locale locale) {
        String key = "category." + category.name();
        return messageSource.getMessage(key, null, locale);
    }
}
