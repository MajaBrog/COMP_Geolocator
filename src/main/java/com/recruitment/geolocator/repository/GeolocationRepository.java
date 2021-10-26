package com.recruitment.geolocator.repository;

import com.recruitment.geolocator.domain.Geolocation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface GeolocationRepository extends CrudRepository<Geolocation, Long> {
    @Override
    List<Geolocation> findAll();

    @Override
    Optional<Geolocation> findById(Long id);

    @Override
    Geolocation save(Geolocation geolocation);

    @Override
    void deleteById(Long id);
}
