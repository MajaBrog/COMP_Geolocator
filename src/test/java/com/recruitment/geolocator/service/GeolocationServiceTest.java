package com.recruitment.geolocator.service;

import com.recruitment.geolocator.domain.Geolocation;
import com.recruitment.geolocator.repository.GeolocationRepository;
import org.junit.Assert;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GeolocationServiceTest {
    @InjectMocks
    private GeolocationService geolocationService;

    @Mock
    private GeolocationRepository geolocationRepository;

    @Test
    public void shouldFetchAllGeolocation() {
        //Given
        Geolocation geolocation1 = new Geolocation(1L, -34.45, 145.67);
        Geolocation geolocation2 = new Geolocation(2L, -46.67, 67.32);
        List<Geolocation> geolocation = new ArrayList<>();
        geolocation.add(geolocation1);
        geolocation.add(geolocation2);

        when(geolocationRepository.findAll()).thenReturn(geolocation);
        //When
        List allGeolocation = geolocationService.getAllGeolocation();
        //Then
        Assert.assertEquals(2, allGeolocation.size());
    }

    @Test
    public void shouldFetchEmptyGeolocationList() {
        //Given
        when(geolocationService.getAllGeolocation()).thenReturn(new ArrayList<>());
        //When
        List allGeolocation = geolocationService.getAllGeolocation();
        //Then
        Assert.assertEquals(0, allGeolocation.size());
    }

    @Test
    public void shouldFetchGeolocation() {
        //Given
        Geolocation geolocation1 = new Geolocation(1L, -34.45, 145.67);
        List<Geolocation> geolocations = new ArrayList<>();
        geolocations.add(geolocation1);

        when(geolocationRepository.findById(1L)).thenReturn(Optional.of(geolocation1));
        //When
        Geolocation result = geolocationService.getGeolocation(1L).get();
        //Then
        Assert.assertEquals(1L, result.getDeviceId(), 0);
        Assert.assertEquals(-34.45, result.getLatitude(),0.001);
        Assert.assertEquals(145.67, result.getLongitude(),0.001);
    }

    @Test
    public void shouldSaveGeolocation() {
        //Given
        Geolocation geolocation1 = new Geolocation(1L, -34.45, 145.67);
        Geolocation updatedGeolocation = new Geolocation(1L, -46.78, 78.98);
        when(geolocationRepository.save(geolocation1)).thenReturn(updatedGeolocation);
        //When
        Geolocation result = geolocationService.saveGeolocation(geolocation1);
        //Then
        Assert.assertEquals(1L, result.getDeviceId(), 0);
        Assert.assertEquals(-46.78, result.getLatitude(),0.001);
        Assert.assertEquals(78.98, result.getLongitude(),0.001);
    }

    @Test
    public void shouldDeleteGeolocation() {

        //When
        geolocationService.deleteGeolocation(1L);
        //Then
        Mockito.verify(geolocationRepository, times(1)).deleteById(1L);
    }
}
