package com.hydroyura.prodms.archive.services.rates;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
//@PropertySource(value = {"application-testing-datalayer"})
@TestPropertySource(
        locations = "classpath:application-test.properties")
class RateServiceTest {

    @Test
    void getAllRates() {
    }

    @Test
    void getDefaultRates() {
    }
}