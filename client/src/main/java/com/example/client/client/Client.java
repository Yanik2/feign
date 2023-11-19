package com.example.client.client;

import com.example.client.config.FeignClientConfiguration;
import java.util.List;
import java.util.Map;
import org.springframework.cloud.openfeign.CollectionFormat;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "testFeignClient", url = "${feign.server.client.url}",
    configuration = FeignClientConfiguration.class)
public interface Client {
  @GetMapping("/hello-from-server")
  String getHelloFromServer();

  @GetMapping("/hello-with-params")
  String helloWithParams(@SpringQueryMap Params params);

  @GetMapping("/hello/{id}")
  String helloWithMatrix(@PathVariable String id, @MatrixVariable MultiValueMap<String, String> vars);

  @GetMapping("/collection/format")
  @CollectionFormat(feign.CollectionFormat.PIPES)
  String collectionFormat(@RequestParam List<String> params);

  @GetMapping("/badRequest")
  ResponseEntity<String> badRequest();

  @GetMapping("/notFound")
  ResponseEntity<String> notFound();

  @GetMapping("/serverError")
  ResponseEntity<String> serverError();

  @GetMapping("/ok")
  ResponseEntity<String> ok();

  @GetMapping("/created")
  ResponseEntity<String> created();

  @GetMapping("/noContent")
  ResponseEntity<String> noContent();
}
