package com.recruitment.geolocator.mapper;

import com.recruitment.geolocator.domain.Geolocation;
import com.recruitment.geolocator.domain.GeolocationDto;
import org.junit.Assert;
import org.junit.Test;


import java.util.ArrayList;
import java.util.List;

public class GeolocationMapperTest{
    @Test
    public void MapToGeolocationTest(){
        //Given
        GeolocationMapper geolocationMapper = new GeolocationMapper();
        GeolocationDto geolocationDto = new GeolocationDto(1L,34.45,151.67);
        //When
        Geolocation geolocation = geolocationMapper.mapToGeolocation(geolocationDto);
        //Then
        Assert.assertEquals(geolocationDto.getDeviceId(),geolocation.getDeviceId());
        Assert.assertEquals(geolocationDto.getLatitude(), geolocation.getLatitude(),0.001);
        Assert.assertEquals(geolocationDto.getLongitude(),geolocation.getLongitude(),0.001);

    }

    @Test
    public void MapToGeolocationDtoTest(){
        //Given
        GeolocationMapper geolocationMapper = new GeolocationMapper();
        Geolocation geolocation = new Geolocation(1L,34.45,151.67);
        //When
        GeolocationDto geolocationDto = geolocationMapper.mapToGeolocationDto(geolocation);
        //Then
        Assert.assertEquals(geolocation.getDeviceId(),geolocationDto.getDeviceId());
        Assert.assertEquals(geolocation.getLatitude(),geolocationDto.getLatitude(),0.001);
        Assert.assertEquals(geolocation.getLongitude(),geolocationDto.getLongitude(),0.001);
    }

    @Test
    public void MapToGeolocationDtoListTest(){
        //Given
        GeolocationMapper geolocationMapper = new GeolocationMapper();
        List<Geolocation> geolocationList = new ArrayList<>();
        Geolocation geolocation = new Geolocation(1L,34.45,151.67);
        geolocationList.add(geolocation);
        //When
        List<GeolocationDto> geolocationDtoList = geolocationMapper.mapToGeolocationDtoList(geolocationList);
        //Then
        Assert.assertEquals(geolocationList.get(0).getDeviceId(),geolocationDtoList.get(0).getDeviceId());
        Assert.assertEquals(geolocationList.get(0).getLatitude(),geolocationDtoList.get(0).getLatitude(),0.001);
        Assert.assertEquals(geolocationList.get(0).getLongitude(),geolocationDtoList.get(0).getLongitude(),0.001);
    }
}