package com.hydroyura.prodms.archive.services.parts;

import com.hydroyura.prodms.archive.configuration.ModelMapperConfiguration;
import com.hydroyura.prodms.archive.services.changes.PartChangeService;
import com.hydroyura.prodms.archive.services.predicates.IPredicateGenerator;
import com.hydroyura.prodms.archive.services.predicates.PartChangePredicateGenerator;
import com.hydroyura.prodms.archive.services.predicates.PartPredicateGenerator;
import com.hydroyura.prodms.archive.services.publisher.Publisher;
import com.hydroyura.prodms.archive.services.publisher.Subscriber;
import com.hydroyura.prodms.archive.services.publisher.partchange.PartChangePublisher;
import com.hydroyura.prodms.archive.services.publisher.partchange.PartChangeSubscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@TestConfiguration
@Import(ModelMapperConfiguration.class)
public class PartServiceTestConfiguration {


    @Bean
    public PartService partService() {
        return new PartService();
    }

    @Bean
    public PartChangeService partChangeService() {
        return new PartChangeService();
    }

    @Bean(value = "PartPredicateGenerator")
    public IPredicateGenerator partPredicateGenerator() {
        return new PartPredicateGenerator();
    }

    @Bean(value = "PartChangePredicateGenerator")
    public IPredicateGenerator partChangePredicateGenerator() {
        return new PartChangePredicateGenerator();
    }

    @Bean(value = "partChangePublisher")
    public Publisher partChangePublisher() {
        return new PartChangePublisher();
    }

    @Bean(value = "partChangeSubscriber")
    public Subscriber partChangeSubscriber() {
        return new PartChangeSubscriber();
    }

    @Bean
    public PartComparator partComparator() {
        return new PartComparator();
    };

}