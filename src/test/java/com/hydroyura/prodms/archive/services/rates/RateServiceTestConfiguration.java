package com.hydroyura.prodms.archive.services.rates;

import com.hydroyura.prodms.archive.configuration.ModelMapperConfiguration;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@TestConfiguration
@Import(ModelMapperConfiguration.class)
public class RateServiceTestConfiguration {

    @Bean
    RateService rateService() {
        return new RateService();
    }

}
