package bg.uni.sofia.hiker.monti.kafka;

public enum TopicName {
    SUGGESTED_PLACES("suggested-places-v1");

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

