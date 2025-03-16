package bg.uni.sofia.hiker.monti.features;

import java.util.HashSet;
import java.util.Set;

public class FeatureFilter {

    public Set<Feature> filter(double latitude, double longitude, double radius, Set<Feature> features) {
        Set<Feature> inRadius = new HashSet<>();

        for (Feature feature : features) {
            double objLon = feature.coordinates()[0];
            double objLat = feature.coordinates()[1];

            double distance = haversine(latitude, longitude, objLat, objLon);

            if (distance <= radius) {
                inRadius.add(feature);
            }
        }

        return inRadius;
    }

    private static double haversine(double lat1, double lon1, double lat2, double lon2) {
        final double R = 6371000;
        lat1 = Math.toRadians(lat1);
        lon1 = Math.toRadians(lon1);
        lat2 = Math.toRadians(lat2);
        lon2 = Math.toRadians(lon2);
        double dlat = lat2 - lat1;
        double dlon = lon2 - lon1;

        double a = Math.sin(dlat / 2) * Math.sin(dlat / 2) +
            Math.cos(lat1) * Math.cos(lat2) *
                Math.sin(dlon / 2) * Math.sin(dlon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c;
    }
}
