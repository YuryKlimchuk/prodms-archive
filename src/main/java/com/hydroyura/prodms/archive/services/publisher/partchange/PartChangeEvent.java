package com.hydroyura.prodms.archive.services.publisher.partchange;

import com.hydroyura.prodms.archive.data.entities.DBPart;
import com.hydroyura.prodms.archive.services.publisher.Event;

public class PartChangeEvent implements Event<PartChangeEventType> {

    private PartChangeEventType type;
    private DBPart content;
    private Class<DBPart> contentType;


    private PartChangeEvent() {
        throw new RuntimeException("Don't use constructor without fields");
    }

    public PartChangeEvent(PartChangeEventType type, DBPart content, Class<DBPart> contentType) {
        this.type = type;
        this.content = content;
        this.contentType = contentType;
    }


    @Override
    public PartChangeEventType getEventType() {
        return type;
    }

    @Override
    public Object getContent() {
        return content;
    }

    @Override
    public Class<?> getContextType() {
        return contentType;
    }

    @Override
    public String getUser() {
        return "YURY_KLIMCHUK";
    }
}
