package io.github.hapjava.characteristics.impl.rtp;

import io.github.hapjava.characteristics.Base64TlvCharacteristic;
import io.github.hapjava.characteristics.EventableCharacteristic;
import io.github.hapjava.characteristics.HomekitCharacteristicChangeCallback;
import io.github.hapjava.server.impl.pairing.TypeLengthValueUtils;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Supplier;
import org.bouncycastle.util.encoders.Base64;

public class StreamingStatusCharacteristic extends Base64TlvCharacteristic
    implements EventableCharacteristic {
  /** Default constructor */
  public StreamingStatusCharacteristic(
      Supplier<CompletableFuture<Short>> streamingStatusGetter,
      Consumer<HomekitCharacteristicChangeCallback> subscriber,
      Runnable unsubscriber) {
    super("00000120-0000-1000-8000-0026BB765291", "Streaming Status", true, false);
    this.getter = streamingStatusGetter;
    this.subscriber = subscriber;
    this.unsubscriber = unsubscriber;
  }

  Supplier<CompletableFuture<Short>> getter;
  Consumer<HomekitCharacteristicChangeCallback> subscriber;
  Runnable unsubscriber;

  @Override
  protected void setValue(String value) throws Exception {}

  @Override
  protected CompletableFuture<String> getValue() {
    return getter
        .get()
        .thenApply(
            status -> {
              TypeLengthValueUtils.Encoder encoder =
                  TypeLengthValueUtils.getEncoder().add((short) 0x01, status);
              return Base64.toBase64String(encoder.toByteArray());
            });
  }

  @Override
  public void subscribe(HomekitCharacteristicChangeCallback callback) {
    subscriber.accept(callback);
  }

  @Override
  public void unsubscribe() {
    unsubscriber.run();
  }
}
