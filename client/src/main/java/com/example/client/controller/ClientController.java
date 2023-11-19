package com.example.client.controller;

import com.example.client.client.Client;
import com.example.client.client.ClientWithFallback;
import com.example.client.client.ClientWithFallbackFactory;
import com.example.client.client.Params;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ClientController {
  private final Client client;
  private final ApplicationContext applicationContext;
  private final ClientWithFallback clientWithFallback;
  private final ClientWithFallbackFactory clientWithFallbackFactory;

  @GetMapping("/hello")
  public String hello() {
    log.info("Client sending request");
    final var response = client.getHelloFromServer();
    log.info("Client received response from server: {}", response);
    return response;
  }

  @GetMapping("/fallback")
  public String fallback() {
    clientWithFallback.badRequest();
    return "fallback";
  }

  @GetMapping("/fallback/factory")
  public ResponseEntity<String> fallbackFactory() {
    return clientWithFallbackFactory.badRequest();
  }

  @GetMapping("/params")
  public String params() {
    client.helloWithParams(new Params("paramFirst", "paramSecond"));
    return "ok";
  }

  @GetMapping("/matrix")
  public String matrix() {
    final var map = new LinkedMultiValueMap<String, String>();
    map.put("matrix1", List.of("one"));
    client.helloWithMatrix("someId", map);
    return "ok";
  }

  @GetMapping("/collection/format")
  public String collectionFormat() {
    final var list = List.of("asdf", "zxcv");
    client.collectionFormat(list);
    return "ok";
  }

  @GetMapping("/status/{status}")
  public Object status(@PathVariable String status) {
    return switch (status) {
      case "badRequest" -> client.badRequest();
      case "serverError" -> client.serverError();
      case "notFound" -> client.notFound();
      case "ok" -> client.ok();
      case "created" -> client.created();
      case "noContent" -> client.noContent();
      default -> null;
    };
  }
}
