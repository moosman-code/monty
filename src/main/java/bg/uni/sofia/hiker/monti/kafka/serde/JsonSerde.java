package bg.uni.sofia.hiker.monti.kafka.serde;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

import java.io.IOException;
import java.util.Map;

public class JsonSerde<T> implements Serde<T> {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Class<T> type;

    public JsonSerde(Class<T> type) {
        this.type = type;
    }

    @Override
    public Serializer<T> serializer() {
        return new Serializer<>() {
            @Override
            public byte[] serialize(String topic, T data) {
                try {
                    return objectMapper.writeValueAsBytes(data);
                } catch (IOException e) {
                    throw new RuntimeException("Failed to serialize object", e);
                }
            }

            @Override
            public void configure(Map<String, ?> configs, boolean isKey) {}

            @Override
            public void close() {}
        };
    }

    @Override
    public Deserializer<T> deserializer() {
        return new Deserializer<>() {
            @Override
            public T deserialize(String topic, byte[] data) {
                try {
                    return objectMapper.readValue(data, type);
                } catch (IOException e) {
                    throw new RuntimeException("Failed to deserialize object", e);
                }
            }

            @Override
            public void configure(Map<String, ?> configs, boolean isKey) {}

            @Override
            public void close() {}
        };
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {}

    @Override
    public void close() {}
}
