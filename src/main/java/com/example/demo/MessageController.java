package com.example.demo;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class MessageController {
    private final List<String> messages = new ArrayList<>();

    // curl -i http://localhost:8080/messages
    @GetMapping("messages")
    public ResponseEntity<List<String>> getMessages() {
        return ResponseEntity.ok(messages);
    }

    // curl -i -X POST http://localhost:8080/messages -H "Content-Type: application/json" -d "first line"
    // curl -i -X POST http://localhost:8080/messages -H "Content-Type: application/json" -d "second line"
    @PostMapping("messages")
    public ResponseEntity<Void> addMessage(@RequestBody String text) {
        messages.add(text);
        return ResponseEntity.accepted().build();
    }

    // curl -i http://localhost:8080/messages/0
    // curl -i http://localhost:8080/messages/1
    // ...
    @GetMapping("messages/{index}")
    public ResponseEntity<String> getMessage(@PathVariable("index") Integer
                                                     index) {
        return ResponseEntity.ok(messages.get(index));
    }

    // curl -i -X DELETE http://localhost:8080/messages/0
    // curl -i -X DELETE http://localhost:8080/messages/1
    // ...
    @DeleteMapping("messages/{index}")
    public ResponseEntity<Void> deleteText(@PathVariable("index") Integer
                                                   index) {
        messages.remove((int) index);
        return ResponseEntity.noContent().build();
    }

    // curl -i -X PUT http://localhost:8080/messages/0 -H "Content-Type: application/json" -d "replaced first line"
    @PutMapping("messages/{index}")
    public ResponseEntity<Void> updateMessage(
            @PathVariable("index") Integer i,
            @RequestBody String message) {
        messages.remove((int) i);
        messages.add(i, message);
        return ResponseEntity.accepted().build();
    }
}
