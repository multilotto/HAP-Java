package io.github.hapjava.characteristics.impl.rtp;

import io.github.hapjava.characteristics.Base64TlvCharacteristic;
import io.github.hapjava.characteristics.impl.rtp.types.RTPConfigTypes;
import io.github.hapjava.server.impl.pairing.TypeLengthValueUtils;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;
import org.bouncycastle.util.encoders.Base64;

public class SupportedRTPConfigurationCharacteristic extends Base64TlvCharacteristic {
  /** Default constructor */
  public SupportedRTPConfigurationCharacteristic(
      Supplier<CompletableFuture<Short>> rtpCryptoSuite) {
    super("00000116-0000-1000-8000-0026BB765291", "Supported RTP Configuration", true, false);
    this.getter = rtpCryptoSuite;
  }

  private Supplier<CompletableFuture<Short>> getter;

  @Override
  protected void setValue(String value) throws Exception {}

  @Override
  protected CompletableFuture<String> getValue() {
    return getter
        .get()
        .thenApply(
            crypto -> {
              TypeLengthValueUtils.Encoder encoder = TypeLengthValueUtils.getEncoder();
              encoder.add(RTPConfigTypes.CRYPTO, crypto);
              return Base64.toBase64String(encoder.toByteArray());
            });
  }
}
