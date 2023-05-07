package com.hydroyura.prodms.archive.services.listeners;

import java.util.Map;

public class EntityChangeMessage {

    private String operation;
    private Object object;
    private Map<String, String> map;

    private EntityChangeMessage() {}

    public String getOperation() {
        return operation;
    }

    public Object getObject() {
        return object;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public static class EntityChangeMessageBuilder {

        private String operation;
        private Object object;
        private Map<String, String> map;

        public EntityChangeMessageBuilder setOperation(String operation) {
            this.operation = operation;
            return this;
        }

        public EntityChangeMessageBuilder setObject(Object object) {
            this.object = object;
            return this;
        }

        public EntityChangeMessageBuilder setMap(Map map) {
            this.map = map;
            return this;
        }

        public EntityChangeMessage build() {
            EntityChangeMessage message = new EntityChangeMessage();
            message.operation = this.operation;
            message.object = this.object;
            message.map = this.map;
            return message;
        }

    }

}
