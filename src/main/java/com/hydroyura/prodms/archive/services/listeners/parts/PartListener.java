package com.hydroyura.prodms.archive.services.listeners.parts;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hydroyura.prodms.archive.data.entities.DBPart;
import com.hydroyura.prodms.archive.data.entities.DBPartChange;
import com.hydroyura.prodms.archive.data.entities.DBPartChangeKey;
import com.hydroyura.prodms.archive.data.repositories.BaseRepository;
import com.hydroyura.prodms.archive.dto.DTOPart;
import com.hydroyura.prodms.archive.services.listeners.EntityChangeMessage;
import com.hydroyura.prodms.archive.services.listeners.IEntityChangeListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component(value = "PartListener")
public class PartListener implements IEntityChangeListener<DTOPart> {

    @Autowired @Qualifier(value = "PartChangeRepository")
    private BaseRepository<DBPartChange, DBPartChangeKey> changeRepository;

    @Autowired @Qualifier(value = "PartRepository")
    private BaseRepository<DBPart, String> partRepository;

    @Autowired
    private ObjectMapper objectMapper;


    // FIXME: User name must be edit
    @Override
    public void receiveMessage(EntityChangeMessage message) throws JsonProcessingException {
        if(message.getObject() instanceof DTOPart part) {
            DBPartChangeKey key = new DBPartChangeKey();
            key.setVersion(part.getVersion());
            key.setPartNumber(part.getNumber());

            DBPartChange change = new DBPartChange();
            change.setKey(key);
            change.setPart(partRepository.getReferenceById(part.getNumber()));
            change.setUser("YURY KLIMCHUK");
            change.setUpdate(part.getUpdated());
            change.setFieldName(String.join(",", message.getMap().keySet()));
            change.setFieldValue(String.join(",", message.getMap().values()));
            change.setOperation(message.getOperation());
            change.setObject(objectMapper.writeValueAsString(part));

            changeRepository.save(change);
        }
    }
}
