package com.recruitment.geolocator.controller;

import com.google.gson.Gson;
import com.recruitment.geolocator.domain.Geolocation;
import com.recruitment.geolocator.domain.GeolocationDto;
import com.recruitment.geolocator.mapper.GeolocationMapper;
import com.recruitment.geolocator.service.GeolocationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(GeolocationController.class)
public class GeolocationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GeolocationService geolocationService;

    @MockBean
    private GeolocationMapper geolocationMapper;

    @Test
    public void shouldFetchEmptyGeolocation() throws Exception {
        List<GeolocationDto> geolocationDtoList = new ArrayList<>();
        when(geolocationMapper.mapToGeolocationDtoList(geolocationService.getAllGeolocation())).thenReturn(geolocationDtoList);

        //When & Then
        mockMvc.perform(get("/v1/geolocation")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void shouldFetchGeolocationList() throws Exception {
        List<GeolocationDto> geolocationDtoList = new ArrayList<>();
        geolocationDtoList.add(new GeolocationDto(1L, 44.45, 165.35));
        when(geolocationMapper.mapToGeolocationDtoList(geolocationService.getAllGeolocation())).thenReturn(geolocationDtoList);

        //When & Then
        mockMvc.perform(get("/v1/geolocation")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].deviceId", is(1)))
                .andExpect(jsonPath("$[0].latitude", is(44.45)))
                .andExpect(jsonPath("$[0].longitude", is(165.35)));
    }

    @Test
    public void shouldFetchGeolocation() throws Exception {
        //Given
        GeolocationDto geolocationDto = new GeolocationDto(1L, 42.12, 167.34);
        Geolocation geolocation = new Geolocation(1L, 42.12, 167.34);

        when(geolocationService.getGeolocation(1L)).thenReturn(Optional.of(geolocation));
        when(geolocationMapper.mapToGeolocationDto(geolocation)).thenReturn(geolocationDto);

        //When & Then
        mockMvc.perform(get("/v1/geolocation/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.deviceId", is(1)))
                .andExpect(jsonPath("$.latitude", is(42.12)))
                .andExpect(jsonPath("$.longitude", is(167.34)));
    }

    @Test
    public void shouldDeleteGeolocation() throws Exception {
        //When & Then
        mockMvc.perform(delete("/v1/geolocation/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldUpdateGeolocation() throws Exception {
        //Given
        GeolocationDto geolocationDto = new GeolocationDto(1L, 56.34, 145.23);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(geolocationDto);

        when(geolocationMapper.mapToGeolocationDto(geolocationService.saveGeolocation(geolocationMapper.mapToGeolocation(geolocationDto)))).thenReturn(geolocationDto);
        //When & Then
        mockMvc.perform(put("/v1/geolocation").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.deviceId", is(1)))
                .andExpect(jsonPath("$.latitude", is(56.34)))
                .andExpect(jsonPath("$.longitude", is(145.23)));

    }

    @Test
    public void shouldCreateGeolocation() throws Exception {
        //Given
        GeolocationDto geolocationDto = new GeolocationDto(1L, 23.45, 167.34);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(geolocationDto);

        //When & Then
        mockMvc.perform(post("/v1/geolocation").contentType(MediaType.APPLICATION_JSON_VALUE)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

}
