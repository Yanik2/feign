package com.example.client.fallback;

import com.example.client.client.ClientWithFallbackFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class CustomFallbackFactory implements FallbackFactory<ClientWithFallbackFactory> {
  @Override
  public ClientWithFallbackFactory create(Throwable cause) {
    return new ClientWithFallbackFactoryFallback(cause);
  }
}
