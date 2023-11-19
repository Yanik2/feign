package com.example.client.client;

import com.example.client.fallback.CustomFallbackFactory;
import com.example.client.fallback.Fallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "clientWithFallbackFactory", url = "${feign.server.client.url}",
    fallbackFactory = CustomFallbackFactory.class)
public interface ClientWithFallbackFactory {

  @GetMapping("/badRequest")
  ResponseEntity<String> badRequest();
}
