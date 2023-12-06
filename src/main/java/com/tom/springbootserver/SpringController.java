package com.tom.springbootserver;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/newIP")
    public ResponseEntity<String> updateIPs(@RequestParam String newIP){

        return ResponseEntity.ok("Done");
    }

    @GetMapping("/getIP")
    public ResponseEntity<String> getIP(@RequestParam String username){
        return ResponseEntity.ok("new IP");
    }

    @PostMapping("/whiteList")
    public ResponseEntity<String> whiteList(@RequestParam String username, @RequestParam String publicKey){
        return ResponseEntity.ok("Updated/overwritten database with user");
    }


    // have private variable for current IP, if different then update database and update variable
}
