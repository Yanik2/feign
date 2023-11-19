package com.example.server.controller;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ServerController {
  @GetMapping("/hello-from-server")
  public String helloFromServer(HttpServletRequest request) {
    authorize(request);
    log.info("Server received request");
    return "Hello from server";
  }

  private void authorize(HttpServletRequest request) {
    final var auth = request.getHeader("Authorization");
    if ("not authorized".equals(auth))
      throw new RuntimeException("not authorized");
  }

  @GetMapping("/hello-with-params")
  public String helloWithParams(@RequestParam Map<String, String> params) {
    log.info("Request with params: {}", params);
    return "Hello from server with params";
  }

  @GetMapping("/hello/{id}")
  public String helloMatrix(@PathVariable String id, @MatrixVariable Map<String, String> vars) {
    log.info("Request with matrix variables: {}", vars);
    return "Hello from matrix variables";
  }

  @GetMapping("/collection/format")
  public String collectionFormat(@RequestParam List<String> params) {
    log.info("Request with collection format: {}", params);
    return "Hello from collection format";
  }

  @GetMapping("/badRequest")
  public ResponseEntity<String> badRequest() {
    return ResponseEntity.badRequest().body("badRequest");
  }

  @GetMapping("/notFound")
  public ResponseEntity<String> notFound() {
    return ResponseEntity.notFound().build();
  }

  @GetMapping("/serverError")
  public ResponseEntity<String> serverError() {
    return ResponseEntity.internalServerError().body("serverError");
  }

  @GetMapping("/ok")
  public ResponseEntity<String> redirect() {
    return ResponseEntity.ok().body("ok");
  }

  @GetMapping("/created")
  public ResponseEntity<String> created() {
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @GetMapping("/noContent")
  public ResponseEntity<String> noContent() {
    return ResponseEntity.noContent().build();
  }
}
