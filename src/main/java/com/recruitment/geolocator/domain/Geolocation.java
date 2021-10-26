package com.recruitment.geolocator.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Geolocation {
    @Id
    public Long deviceId;
    @Column
    public double latitude;
    @Column
    public double longitude;
}
