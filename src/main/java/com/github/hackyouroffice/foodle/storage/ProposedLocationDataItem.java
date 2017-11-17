package com.github.hackyouroffice.foodle.storage;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.hackyouroffice.foodle.Location;

@DynamoDBTable(tableName = "ProposedLocationData")
public class ProposedLocationDataItem {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private String name;

    private ProposedLocation proposedLocation;

    @DynamoDBHashKey(attributeName = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @DynamoDBAttribute(attributeName = "Data")
    @DynamoDBMarshalling(marshallerClass = ProposedLocationDataMarshaller.class)
    public ProposedLocation getProposedLocation() {
        return proposedLocation;
    }

    public void setProposedLocation(ProposedLocation location) {
        this.proposedLocation = location;
    }

    /**
     * A {@link DynamoDBMarshaller} that provides marshalling and unmarshalling logic for
     * {@link Location} values so that they can be persisted in the database as String.
     */
    public static class ProposedLocationDataMarshaller implements DynamoDBMarshaller<ProposedLocation> {

        @Override
        public String marshall(ProposedLocation gameData) {
            try {
                return OBJECT_MAPPER.writeValueAsString(gameData);
            } catch (JsonProcessingException e) {
                throw new IllegalStateException("Unable to marshall proposedLocation data", e);
            }
        }

        @Override
        public ProposedLocation unmarshall(Class<ProposedLocation> clazz, String value) {
            try {
                return OBJECT_MAPPER.readValue(value, new TypeReference<ProposedLocation>() {
                });
            } catch (Exception e) {
                throw new IllegalStateException("Unable to unmarshall proposedLocation data value", e);
            }
        }
    }
}
