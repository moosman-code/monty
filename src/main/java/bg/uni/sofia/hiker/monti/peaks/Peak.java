package bg.uni.sofia.hiker.monti.peaks;

public record Peak(
    String id,
    String name,
    double[] coordinates,
    String type,
    String fact
) {
}
