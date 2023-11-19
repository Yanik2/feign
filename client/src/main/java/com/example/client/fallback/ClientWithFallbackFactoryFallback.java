package com.example.client.fallback;

import com.example.client.client.ClientWithFallbackFactory;
import feign.FeignException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

public class ClientWithFallbackFactoryFallback implements ClientWithFallbackFactory {
  private Throwable cause;

  public ClientWithFallbackFactoryFallback(Throwable cause) {
    this.cause = cause;
  }

  @Override
  public ResponseEntity<String> badRequest() {
    final var ex = (FeignException) cause;
    return ResponseEntity.ok("There was an error with status " + ex.status() +
        "and body " + ex.responseBody());
  }
}
