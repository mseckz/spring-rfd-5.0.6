package com.example.demo.controller;

import com.example.demo.dto.Hello;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VulnerableController {

    @GetMapping("/vulnerable")
    public ResponseEntity<Hello> hello(@RequestParam("name") String name, @RequestParam("filename") String filename) {
        Hello hello = new Hello();
        hello.setName(name);
        ContentDisposition contentDisposition = ContentDisposition.builder("attachment")
                .filename(filename + ".txt").build();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(contentDisposition);
        return ResponseEntity.ok().headers(headers).body(hello);
    }


    @GetMapping("/not-vulnerable")
    public ResponseEntity<Hello> helloSecure(@RequestParam("name") String name, @RequestParam("filename") String filename) {
        Hello hello = new Hello();
        hello.setName(name);
        filename = filename.replace("\"", "");
        ContentDisposition contentDisposition = ContentDisposition.builder("attachment")
                .filename(filename + ".txt").build();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(contentDisposition);
        return ResponseEntity.ok().headers(headers).body(hello);
    }

}
