package com.recruitment.geolocator.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GeolocationDto {
    public Long deviceId;
    public double latitude;
    public double longitude;
}

