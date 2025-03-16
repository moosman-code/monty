package bg.uni.sofia.hiker.monti.features;

public abstract class Feature {
    private final String id;
    private final String name;
    private final double[] coordinates;
    private final String type;
    private String fact;

    public Feature(String id, String name, double[] coordinates, String type, String fact) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.type = type;
        this.fact = fact;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double[] getCoordinates() {
        return coordinates;
    }

    public String getType() {
        return type;
    }

    public String getFact() {
        return fact;
    }

    public void setFact(String fact) {
        if (fact != null && !fact.isEmpty()) {
            this.fact = fact;
        }
    }
}
