package com.hydroyura.prodms.archive.services.publisher.partchange;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hydroyura.prodms.archive.data.entities.DBPart;
import com.hydroyura.prodms.archive.data.entities.DBPartChange;
import com.hydroyura.prodms.archive.data.entities.dto.DTOPart;
import com.hydroyura.prodms.archive.data.entities.dto.DTOPartChange;
import com.hydroyura.prodms.archive.data.entities.keys.DBPartChangeKey;
import com.hydroyura.prodms.archive.services.changes.PartChangeService;
import com.hydroyura.prodms.archive.services.publisher.Event;
import com.hydroyura.prodms.archive.services.publisher.Subscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class PartChangeSubscriber implements Subscriber {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PartChangeService partChangeService;

    @Override
    public void handleEvent(Event event) {
        partChangeService.create(buildPartChange(event));
    }

    private DBPartChange buildPartChange(Event ev) {
        PartChangeEvent event = PartChangeEvent.class.cast(ev);
        DBPart part = DBPart.class.cast(event.getContent());
        DBPartChangeKey key = new DBPartChangeKey()
                .setPartNumber(part.getNumber())
                .setVersion(part.getVersion());
        try {
            DBPartChange partChange = new DBPartChange()
                    .setPart(part)
                    .setOperation(event.getEventType())
                    .setUser(event.getUser())
                    .setUpdate(LocalDate.now())
                    .setObject(objectMapper.writeValueAsString(part))
                    .setKey(key);
            return partChange;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}