package bg.uni.sofia.hiker.monti.controller;

import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bg.uni.sofia.hiker.monti.features.Feature;
import bg.uni.sofia.hiker.monti.features.FeatureFilter;
import bg.uni.sofia.hiker.monti.kafka.consumer.FeatureExtractor;

@RestController
@RequestMapping("/monti")
public class MontiController {

    private final FeatureExtractor featureExtractor = new FeatureExtractor();
    private final FeatureFilter featureFilter = new FeatureFilter();

    @GetMapping("/features")
    public Set<Feature> getFeatures(@RequestParam String latitude,
                                    @RequestParam String longitude,
                                    @RequestParam String radius) {
        Set<Feature> extracted = featureExtractor.extract();
        featureFilter.filter(Double.parseDouble(latitude),
                             Double.parseDouble(longitude),
                             Double.parseDouble(radius),
                             extracted);
    }
}
