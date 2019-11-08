package org.github.omnbmh.cobra.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public ResponseEntity hello() {
        return ResponseEntity.ok().body("Hello Cobra!");
    }

    @RequestMapping("/admin/hello")
    public String admin_hello() {
        return "Hello Admin Cobra!";
    }

    @RequestMapping("/user/hello")
    public String user_hello() {
        return "Hello User Cobra!";
    }
}
