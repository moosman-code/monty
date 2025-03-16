package bg.uni.sofia.hiker.monti.controller;

import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bg.uni.sofia.hiker.monti.kafka.consumer.PeakExtractor;
import bg.uni.sofia.hiker.monti.features.Peak;

@RestController
@RequestMapping("/monti")
public class MontiController {

    private final PeakExtractor peakExtractor = new PeakExtractor();

    @GetMapping("/hello")
    public Set<Peak> getFeatures() {
        return peakExtractor.extract();

    }
}
