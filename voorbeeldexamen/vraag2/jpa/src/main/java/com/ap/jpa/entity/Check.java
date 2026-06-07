package com.ap.jpa.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity 
@Table(name = "check_results")
public class Check {
    
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "col_dateToCheck")
    private LocalDate dateToCheck;

    @Column(name = "col_checked")
    private boolean checked;

    @Column(name = "col_days")
    private int days;

    public Check() {
    }

    public Check(LocalDate dateToCheck, boolean checked, int days) {
        this.dateToCheck = dateToCheck;
        this.checked = checked;
        this.days = days;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateToCheck() {
        return dateToCheck;
    }

    public void setDateToCheck(LocalDate dateToCheck) {
        this.dateToCheck = dateToCheck;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    
    
}
