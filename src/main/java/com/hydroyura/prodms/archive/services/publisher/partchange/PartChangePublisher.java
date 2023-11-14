package com.hydroyura.prodms.archive.services.publisher.partchange;

import com.hydroyura.prodms.archive.services.publisher.Event;
import com.hydroyura.prodms.archive.services.publisher.Publisher;
import com.hydroyura.prodms.archive.services.publisher.Subscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class PartChangePublisher implements Publisher {

    @Autowired(required = false)
    private Collection<Subscriber> subscribers = new ArrayList<>();

    @Override
    public void subscribe(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void unsubscribe(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    @Override
    public void sendEvent(Event event) {
        subscribers.forEach(subscriber -> subscriber.handleEvent(event));
    }
}
