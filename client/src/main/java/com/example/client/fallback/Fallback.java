package com.example.client.fallback;

import com.example.client.client.ClientWithFallback;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class Fallback implements ClientWithFallback {
  @Override
  public String test() {
    System.out.println("Fallback");
    throw new RuntimeException("Exception from fallback");
  }

  @Override
  public ResponseEntity<String> badRequest() {
    System.out.println("fallback");
    return null;
  }
}
