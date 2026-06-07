package com.ap.students.controller;

import java.time.LocalDate;

public record StudentDTO(
    String firstName,
    String lastName,
    LocalDate dateOfBirth,
    String studyProgram
) {

}
