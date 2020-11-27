package io.github.hapjava.services.impl;

import java.util.concurrent.CompletableFuture;
import javax.json.JsonObject;

public interface ResourceService {
  String getResourceType();

  CompletableFuture<byte[]> handle(JsonObject request);
}
