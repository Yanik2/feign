package com.example.client.encoder;

import com.example.client.client.Params;
import feign.QueryMapEncoder;
import java.util.Map;

public class CustomQueryMapEncoder implements QueryMapEncoder {
  @Override
  public Map<String, Object> encode(Object object) {
    final var params = (Params) object;
    return Map.of("paramFirst", params.param1(),
        "paramSecond", params.param2(),
        "paramThird", "customParameterFromCustomEncoder");
  }
}
