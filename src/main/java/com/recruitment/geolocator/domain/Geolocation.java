package com.recruitment.geolocator.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Geolocation {
    @Id
    @NotNull
    public Long deviceId;

    @Min(value = -90)
    @Max(value = 90)
    public double latitude;

    @Min(value = -180)
    @Max(value = 180)
    public double longitude;
}
