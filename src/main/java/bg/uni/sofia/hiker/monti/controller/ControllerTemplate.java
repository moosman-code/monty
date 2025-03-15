package bg.uni.sofia.hiker.monti.controller;

import java.util.List;

import bg.uni.sofia.hiker.monti.model.ModelTemplate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class ControllerTemplate {

    private final ObjectMapper objectMapper = new ObjectMapper();
    //private final EmployeeRepository repository;

//    ControllerTemplate(ModelTemplate repository) {
//        this.repository = repository;
//    }

    @GetMapping("/test-models")
    public ResponseEntity<String> testModels() {
        List<ModelTemplate> models = List.of(
                new ModelTemplate("1", "Whell", "Drinking", 11.123, Pair.of(12.12, 12.12)),
                new ModelTemplate("2", "River", "Water",5.553, Pair.of(12.12, 12.12)),
                new ModelTemplate("3", "Peak", "Mountain",100.003, Pair.of(12.12, 12.12))
                );

        try {
            String json = objectMapper.writeValueAsString(models); // Convert List to JSON
            return ResponseEntity.ok(json);
        } catch (JsonProcessingException e) {
            return ResponseEntity.internalServerError().body("Error converting to JSON");
        }
    }

    @GetMapping("/gemini-test")
    public ResponseEntity<String> geminiTest() {
        ModelTemplate models = new ModelTemplate("1", "Черни връх", "Планина", 11.123, Pair.of(12.12, 12.12));



        try {
            String json = objectMapper.writeValueAsString(models); // Convert List to JSON
            return ResponseEntity.ok(json);
        } catch (JsonProcessingException e) {
            return ResponseEntity.internalServerError().body("Error converting to JSON");
        }
    }

//    @PostMapping("/employees")
//    ModelTemplate newEmployee(@RequestBody Employee newEmployee) {
//        return repository.save(newEmployee);
//    }
//
//    // Single item
//
//    @GetMapping("/employees/{id}")
//    ModelTemplate one(@PathVariable Long id) {
//
//        return repository.findById(id)
//                .orElseThrow(() -> new EmployeeNotFoundException(id));
//    }
//
//    @PutMapping("/employees/{id}")
//    ModelTemplate replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
//
//        return repository.findById(id)
//                .map(employee -> {
//                    employee.setName(newEmployee.getName());
//                    employee.setRole(newEmployee.getRole());
//                    return repository.save(employee);
//                })
//                .orElseGet(() -> {
//                    return repository.save(newEmployee);
//                });
//    }

//    @DeleteMapping("/employees/{id}")
//    void deleteEmployee(@PathVariable Long id) {
//        repository.deleteById(id);
//    }
}