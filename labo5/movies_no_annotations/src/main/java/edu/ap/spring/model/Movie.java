package edu.ap.spring.model;

import java.io.Serializable;
import java.util.List;

/**
 * Immutable Movie record.
 * Serializable is verplicht zodat Spring het object in Redis kan opslaan.
 */
public record Movie(String title, String year, List<String> actors) implements Serializable {

    /**
     * Handig voor weergave in de template.
     */
    @Override
    public String toString() {
        return "%s (%s) — Actors: %s".formatted(title, year, String.join(", ", actors));
    }
}
