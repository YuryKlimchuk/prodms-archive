package com.hydroyura.prodms.archive.services.publisher;

public interface Publisher {

    void subscribe(Subscriber subscriber);
    void unsubscribe(Subscriber subscriber);
    void sendEvent(Event event);

}
