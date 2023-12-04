package com.tom.springbootserver;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/data/param")
    public ResponseEntity<String> returnParam(@RequestParam String param, @RequestParam String param2){
        // will activate when navigate to home/api.data
        // Operations here
        String data = "Hello from another world!"+param+" "+param2;
        return ResponseEntity.ok(data);
    }
}
