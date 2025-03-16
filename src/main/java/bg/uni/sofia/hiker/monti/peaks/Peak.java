package bg.uni.sofia.hiker.monti.peaks;

import java.util.List;

public record Peak(
    String id,
    String ele,
    String name,
    double[] coordinates,
    String type,
    String fact
) {
}
