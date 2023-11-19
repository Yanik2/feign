package com.example.client.config;

import com.example.client.encoder.CustomQueryMapEncoder;
import com.example.client.interceptor.FeignClientRequestInterceptor;
import feign.Logger;
import feign.QueryMapEncoder;
import feign.RequestInterceptor;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class FeignClientConfiguration {

  @Bean
  public RequestInterceptor requestInterceptor() {
    return new FeignClientRequestInterceptor();
  }

  //To enable logging you need to set DEBUG level for particular feign client
  // see application.yaml
  @Bean
  public Logger.Level feignLoggerLevel() {
    return Logger.Level.FULL;
  }

  @Bean
  public QueryMapEncoder queryMapEncoder() {
    return new CustomQueryMapEncoder();
  }

//  @Bean
//  public ErrorDecoder errorDecoder() {
//    return new ErrorDecoder() {
//      @Override
//      public Exception decode(String methodKey, Response response) {
//        return null;
//      }
//    }
//  }
}
