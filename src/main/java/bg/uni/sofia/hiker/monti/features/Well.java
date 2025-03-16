package bg.uni.sofia.hiker.monti.features;

public class Well extends Feature {
    private String id;
    private double[] coordinates;
    private String type;

    public Well(String id, double[] coordinates, String type) {
        super(id, "", coordinates, type, "");
    }
}
