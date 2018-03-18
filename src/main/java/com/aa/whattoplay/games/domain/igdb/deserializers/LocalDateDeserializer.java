package com.aa.whattoplay.games.domain.igdb.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;

public class LocalDateDeserializer extends StdDeserializer<LocalDate> {

    public LocalDateDeserializer() {
        this(null);
    }

    public LocalDateDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public LocalDate deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);
        int year = (Integer) ( node.get("year")).numberValue();
        String month = node.get("month").asText();
        int dayOfMonth = (Integer) ( node.get("dayOfMonth")).numberValue();
        return LocalDate.of(year, Month.valueOf(month).getValue() , dayOfMonth );
    }
}