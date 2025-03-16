package bg.uni.sofia.hiker.monti.controller;

import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bg.uni.sofia.hiker.monti.features.Feature;
import bg.uni.sofia.hiker.monti.kafka.consumer.PeakExtractor;

@RestController
@RequestMapping("/monti")
public class MontiController {

    private final PeakExtractor peakExtractor = new PeakExtractor();

    @GetMapping("/features")
    public Set<Feature> getFeatures() {
        return peakExtractor.extract();

    }
}
