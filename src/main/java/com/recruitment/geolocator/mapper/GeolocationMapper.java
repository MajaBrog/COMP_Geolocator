package com.recruitment.geolocator.mapper;

import com.recruitment.geolocator.domain.Geolocation;
import com.recruitment.geolocator.domain.GeolocationDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GeolocationMapper {
    public Geolocation mapToGeolocation(final GeolocationDto geolocationDto) {
        return new Geolocation(
                geolocationDto.getDeviceId(),
                geolocationDto.getLatitude(),
                geolocationDto.getLongitude());
    }

    public GeolocationDto mapToGeolocationDto(final Geolocation geolocation) {
        return new GeolocationDto(
                geolocation.getDeviceId(),
                geolocation.getLatitude(),
                geolocation.getLongitude());
    }

    public List<GeolocationDto> mapToGeolocationDtoList(final List<Geolocation> geolocationList) {
        return geolocationList.stream()
                .map(g -> new GeolocationDto(g.deviceId, g.latitude, g.longitude))
                .collect(Collectors.toList());
    }
}
