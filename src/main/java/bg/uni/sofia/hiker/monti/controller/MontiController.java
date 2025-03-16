package bg.uni.sofia.hiker.monti.controller;

import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bg.uni.sofia.hiker.monti.kafka.consumer.PeakExtractor;
import bg.uni.sofia.hiker.monti.peaks.Peak;

@RestController
@RequestMapping("/monti")
public class MontiController {

    private final PeakExtractor peakExtractor = new PeakExtractor();

    @GetMapping("/hello")
    public Set<Peak> getPeaks() {
        return (peakExtractor.extract().isEmpty()) ? Set.of(new Peak("1", "velkata", new double[]{23.2906, 42.56904}, "well", "verygud"), new Peak("2", "velkaka", new double[]{23.2886, 42.5622904}, "peak", "yes"), new Peak("3", "velkaka", new double[]{23.2892, 42.564200}, "peak", "omg"), new Peak("4", "veka", new double[]{23.288996, 42.562244}, "peak", "yesaaaa")) : peakExtractor.extract();

    }
}
