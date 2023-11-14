package com.hydroyura.prodms.archive.services.publisher;

public interface Event<EventType extends Enum> {

    public EventType getEventType();
    public Object getContent();
    public Class<?> getContextType();
    public String getUser();

}
