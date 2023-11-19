package com.example.client.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class FeignClientRequestInterceptor implements RequestInterceptor {
  @Override
  public void apply(RequestTemplate template) {
    template.header("Authorization", "Bearer asfasdfasdfasdf");
  }
}
