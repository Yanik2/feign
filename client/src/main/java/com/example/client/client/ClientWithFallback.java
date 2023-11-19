package com.example.client.client;

import com.example.client.fallback.Fallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "clientWithFallback", url = "${feign.server.client.url}",
    fallback = Fallback.class)
public interface ClientWithFallback {

  @GetMapping
  String test();

  @GetMapping("/badRequest")
  ResponseEntity<String> badRequest();
}
