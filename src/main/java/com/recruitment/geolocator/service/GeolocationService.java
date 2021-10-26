package com.recruitment.geolocator.service;

import com.recruitment.geolocator.domain.Geolocation;
import com.recruitment.geolocator.repository.GeolocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GeolocationService {
    @Autowired
    private GeolocationRepository geolocationRepository;

    public List<Geolocation> getAllGeolocation() {
        return geolocationRepository.findAll();
    }

    public Optional<Geolocation> getGeolocation(final Long id) {
        return geolocationRepository.findById(id);
    }

    public Geolocation saveGeolocation(final Geolocation geolocation) {
        return geolocationRepository.save(geolocation);
    }

    public void deleteGeolocation(final Long id) {
        geolocationRepository.deleteById(id);
    }
}