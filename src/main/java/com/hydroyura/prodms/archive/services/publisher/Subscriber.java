package com.hydroyura.prodms.archive.services.publisher;

public interface Subscriber {
    void handleEvent(Event event);
}
