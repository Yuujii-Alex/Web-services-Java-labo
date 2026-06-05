package com.ap.rest.controller;

import com.fasterxml.jackson.annotation.JsonProperty;

public record InfractionDTO(
    @JsonProperty("id") Long id,
    @JsonProperty("year") Integer year,
    @JsonProperty("month") Integer month,
    @JsonProperty("date") String date,
    @JsonProperty("street") String street,
    @JsonProperty("driving_direction") String drivingDirection,
    @JsonProperty("speed_limit") int speedLimit,
    @JsonProperty("passersby") int passersby,
    @JsonProperty("infractions_speed") int infractionsSpeed,
    @JsonProperty("infractions_red_light") int infractionsRedLight
) { }
