package bg.uni.sofia.hiker.monti.kafka.serde;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;
import java.util.Map;

public class JsonDeserializer<T> implements Deserializer<T> {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private Class<T> type;

    // No-argument constructor required by Kafka
    public JsonDeserializer() {
    }

    // Constructor with type parameter (optional, for manual instantiation)
    public JsonDeserializer(Class<T> type) {
        this.type = type;
    }

    @Override
    public T deserialize(String topic, byte[] data) {
        if (data == null) {
            return null;
        }
        try {
            return objectMapper.readValue(data, type);
        } catch (IOException e) {
            throw new RuntimeException("Failed to deserialize object", e);
        }
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        // Extract the type from the configuration
        if (configs.containsKey("value.deserializer.type")) {
            this.type = (Class<T>) configs.get("value.deserializer.type");
        } else {
            throw new RuntimeException("Deserializer type not configured. Set 'value.deserializer.type' in the Kafka consumer properties.");
        }
    }

    @Override
    public void close() {
        // No resources to close
    }
}
