package com.hotel.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.io.IOException;
import java.util.Map;


/**
 * JsonMapConverter is a JPA attribute converter class that allows mapping a Java Map
 * to a JSON string when storing it in the database and vice versa when reading it from
 * the database.
 *
 * @author Abdul Basith
 */
@Converter
public class JsonMapConverter implements AttributeConverter<Map<String, Object>, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Map<String, Object> attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            // Handle the exception appropriately
            throw new RuntimeException("Error converting map to JSON", e);
        }
    }

    @Override
    public Map<String, Object> convertToEntityAttribute(String dbData) {
        try {
//            if(dbData == null){
//                return (Map<String, Object>) new HashMap<>().put("amenities",null);
//            }

            // Use the conditional operator to provide a default value when dbData is null
            dbData = (dbData != null) ? dbData : "{\"amenities\": null}";
            return objectMapper.readValue(dbData, Map.class);
        } catch (IOException e) {
            // Handle the exception appropriately
            throw new RuntimeException("Error converting JSON to map", e);
        }
    }
}
