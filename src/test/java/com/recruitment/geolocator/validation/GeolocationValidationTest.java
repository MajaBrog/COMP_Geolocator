package com.recruitment.geolocator.validation;

import com.recruitment.geolocator.domain.Geolocation;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GeolocationValidationTest {
    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    @BeforeClass
    public static void init() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    public void shouldHaveNoViolations() {
        //Given
        Geolocation geolocation = new Geolocation(1L, 78.00, 160.00);

        //When
        Set<ConstraintViolation<Geolocation>> violations
                = validator.validate(geolocation);

        //Then
        assertTrue(violations.isEmpty());
    }

    @Test
    public void shouldDetectInvalidLatitude() {
        //Given
        Geolocation geolocation = new Geolocation(1L, 100.00, 160.00);

        //when:
        Set<ConstraintViolation<Geolocation>> violations
                = validator.validate(geolocation);

        //then:
        assertEquals(violations.size(), 1);

        ConstraintViolation<Geolocation> violation
                = violations.iterator().next();

        assertEquals("latitude", violation.getPropertyPath().toString());
        assertEquals(100.00, violation.getInvalidValue());
    }

    @Test
    public void shouldDetectInvalidLongitude() {
        //Given
        Geolocation geolocation = new Geolocation(1L, 80.00, 190.00);

        //when:
        Set<ConstraintViolation<Geolocation>> violations
                = validator.validate(geolocation);

        //then:
        assertEquals(violations.size(), 1);

        ConstraintViolation<Geolocation> violation
                = violations.iterator().next();

        assertEquals("longitude", violation.getPropertyPath().toString());
        assertEquals(190.00, violation.getInvalidValue());
    }

    @Test
    public void shouldDetectNullId() {
        //Given
        Geolocation geolocation = new Geolocation(null, 80.00, 160.00);

        //when:
        Set<ConstraintViolation<Geolocation>> violations
                = validator.validate(geolocation);

        //then:
        assertEquals(violations.size(), 1);

        ConstraintViolation<Geolocation> violation
                = violations.iterator().next();

        assertEquals("deviceId", violation.getPropertyPath().toString());
        assertEquals(null, violation.getInvalidValue());
    }

    @AfterClass
    public static void close() {
        validatorFactory.close();
    }
}
