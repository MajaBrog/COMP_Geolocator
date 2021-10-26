package com.recruitment.geolocator.controller;

import com.recruitment.geolocator.domain.GeolocationDto;
import com.recruitment.geolocator.mapper.GeolocationMapper;
import com.recruitment.geolocator.service.GeolocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1")
public class GeolocationController {
    @Autowired
    private GeolocationService geolocationService;
    @Autowired
    private GeolocationMapper geolocationMapper;

    @RequestMapping(method = RequestMethod.GET, value = "/geolocation")
    private List<GeolocationDto> getGeolocations() {
        return geolocationMapper.mapToGeolocationDtoList(geolocationService.getAllGeolocation());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/geolocation/{geolocationId}")
    public GeolocationDto getGeolocation(@PathVariable Long geolocationId) throws RecordNotFoundException {
        return geolocationMapper.mapToGeolocationDto(geolocationService.getGeolocation(geolocationId).orElseThrow(RecordNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/geolocation/{geolocationId}")
    public void deleteGeolocation(@PathVariable Long geolocationId) {
        geolocationService.deleteGeolocation(geolocationId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/geolocation")
    public GeolocationDto updateGeolocation(@RequestBody GeolocationDto geolocationDto) {
        return geolocationMapper.mapToGeolocationDto(geolocationService.saveGeolocation(geolocationMapper.mapToGeolocation(geolocationDto)));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/geolocation", consumes = APPLICATION_JSON_VALUE)
    public void createGeolocation(@RequestBody GeolocationDto geolocationDto) {
        geolocationService.saveGeolocation(geolocationMapper.mapToGeolocation(geolocationDto));
    }


}

