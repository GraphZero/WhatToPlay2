package com.aa.whattoplay.games.domain.igdb.deserializers;

import com.aa.whattoplay.games.domain.igdb.json.TimeToBeatJson;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.Duration;

public class TimeToBeatDeserializer extends StdDeserializer<TimeToBeatJson> {

    public TimeToBeatDeserializer() {
        this(null);
    }

    public TimeToBeatDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public TimeToBeatJson deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);
        long hastly = 0;
        long normally = 0;
        long completely = 0;
        if ( node.get("hastly").get("seconds") != null ){
            hastly = (Integer) ( node.get("hastly")).get("seconds").numberValue();
        }
        if ( node.get("normally").get("seconds") != null ){
            normally = (Integer) ( node.get("normally")).get("seconds").numberValue();
        }
        if ( node.get("completely").get("seconds") != null ){
            completely = (Integer) ( node.get("completely")).get("seconds").numberValue();
        }
        return new TimeToBeatJson(Duration.ofSeconds(hastly), Duration.ofSeconds(normally), Duration.ofSeconds(completely) );
    }
}
