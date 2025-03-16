package bg.uni.sofia.hiker.monti.kafka.topic;

public enum TopicName {
    FEATURES_V1("features-v1");

    private final String value;

    TopicName(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Value of TopicName cannot be null or blank");
        }

        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

