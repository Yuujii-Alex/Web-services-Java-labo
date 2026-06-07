package com.ap.jpa.controller;

import java.time.LocalDate;

public record CheckDTO(
        LocalDate dateToCheck,
        boolean checked,
        int days) {
}
