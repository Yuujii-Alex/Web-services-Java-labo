package com.ap.rest.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "infraction")
public class Infraction {
    
    @Id
    @Column(name = "col_id")
    private Long id;

    @Column(name = "col_year")
    private Integer year;

    @Column(name = "col_month")
    private Integer month;

    @Column(name = "col_date")
    private String date;

    @Column(name = "col_street")
    private String street;

    @Column(name = "col_driving_direction")
    private String drivingDirection;

    @Column(name = "col_speed_limit")
    private int speedLimit;

    @Column(name = "col_passersby")
    private int passersby;

    @Column(name = "col_infractions_speed")
    private int infractionsSpeed;

    @Column(name = "col_infractions_red_light")
    private int infractionsRedLight;

    public Infraction() { }

    public Infraction(Long id, Integer year, Integer month, String date, String street, String drivingDirection,
            int speedLimit, int passersby, int infractionsSpeed, int infractionsRedLight) {
        this.id = id;
        this.year = year;
        this.month = month;
        this.date = date;
        this.street = street;
        this.drivingDirection = drivingDirection;
        this.speedLimit = speedLimit;
        this.passersby = passersby;
        this.infractionsSpeed = infractionsSpeed;
        this.infractionsRedLight = infractionsRedLight;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getDrivingDirection() {
        return drivingDirection;
    }

    public void setDrivingDirection(String drivingDirection) {
        this.drivingDirection = drivingDirection;
    }

    public int getSpeedLimit() {
        return speedLimit;
    }

    public void setSpeedLimit(int speedLimit) {
        this.speedLimit = speedLimit;
    }

    public int getPassersby() {
        return passersby;
    }

    public void setPassersby(int passersby) {
        this.passersby = passersby;
    }

    public int getInfractionsSpeed() {
        return infractionsSpeed;
    }

    public void setInfractionsSpeed(int infractionsSpeed) {
        this.infractionsSpeed = infractionsSpeed;
    }

    public int getInfractionsRedLight() {
        return infractionsRedLight;
    }

    public void setInfractionsRedLight(int infractionsRedLight) {
        this.infractionsRedLight = infractionsRedLight;
    }

    
}
