package bg.uni.sofia.hiker.monti.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/monti")
public class MontiController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello from Monti!";
    }
}
