package com.example.practicalfive;

import java.util.Locale;

/** Validates and normalises municipality names in one place. */
public final class TextProcessor {
    private TextProcessor() {
        // Utility class.
    }

    /**
     * Returns a supported municipality in its display form, or null for invalid input.
     */
    public static String processMunicipality(String municipality) {
        if (municipality == null || municipality.trim().isEmpty()) {
            return null;
        }

        switch (municipality.trim().toLowerCase(Locale.ROOT)) {
            case "beijing":
                return "Beijing";
            case "shanghai":
                return "Shanghai";
            case "tianjin":
                return "Tianjin";
            case "chongqing":
                return "Chongqing";
            default:
                return null;
        }
    }
}
