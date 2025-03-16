package bg.uni.sofia.hiker.monti.features;

public record Feature(
    String id,
    String name,
    double[] coordinates,
    String type,
    String fact) {
}
