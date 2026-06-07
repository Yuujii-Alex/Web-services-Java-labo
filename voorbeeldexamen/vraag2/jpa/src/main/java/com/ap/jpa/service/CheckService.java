package com.ap.jpa.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ap.jpa.controller.CheckDTO;
import com.ap.jpa.dao.CheckRepository;
import com.ap.jpa.entity.Check;

@Service
public class CheckService {

    private final CheckRepository repo;

    public CheckService(CheckRepository repo) {
        this.repo = repo;
    }

    public List<CheckDTO> getChecks() {
        return repo.findAll().stream().map(this::assemble).toList();
    }

    public void addCheck(LocalDate dateToCheck, boolean isChecked, int days) {
         repo.save(new Check(dateToCheck, isChecked, days));
    }

    private CheckDTO assemble(Check check) {
        return new CheckDTO(check.getDateToCheck(), check.isChecked(), check.getDays());
    }
}
