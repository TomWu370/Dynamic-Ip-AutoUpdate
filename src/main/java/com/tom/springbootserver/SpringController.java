package com.tom.springbootserver;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SpringController {

    @GetMapping("/data")
    public ResponseEntity<String> fetchData(){
        // will activate when navigate to home/api.data
        // Operations here
        String data = "Hello from another world!";
        return ResponseEntity.ok(data);
    }
}
