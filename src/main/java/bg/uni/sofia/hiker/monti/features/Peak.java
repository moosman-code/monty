package bg.uni.sofia.hiker.monti.features;

public class Peak extends Feature {

    private String id;
    private String name;
    private double[] coordinates;
    private String type;
    private String fact;

    public Peak(String id, String name, double[] coordinates, String type, String fact) {
        super(id, name, coordinates, type, fact);
    }
}
