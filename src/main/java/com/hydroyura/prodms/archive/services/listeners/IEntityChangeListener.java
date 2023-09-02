package com.hydroyura.prodms.archive.services.listeners;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface IEntityChangeListener<DTO> {

    public void receiveMessage(EntityChangeMessage message);

}
