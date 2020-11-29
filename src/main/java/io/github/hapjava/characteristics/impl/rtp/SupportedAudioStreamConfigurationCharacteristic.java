package io.github.hapjava.characteristics.impl.rtp;

import io.github.hapjava.characteristics.Base64TlvCharacteristic;
import io.github.hapjava.characteristics.impl.rtp.types.AudioCodecTypes;
import io.github.hapjava.server.impl.pairing.TypeLengthValueUtils;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;
import org.bouncycastle.util.encoders.Base64;

public class SupportedAudioStreamConfigurationCharacteristic extends Base64TlvCharacteristic {
  public static class SupportedAudioStreamConfiguration {
    private final List<AudioCodecTypes> supportedCodecs;
    private final boolean comfortNoiseSupport;

    public SupportedAudioStreamConfiguration(
        List<AudioCodecTypes> supportedCodecs, boolean comfortNoiseSupport) {
      this.supportedCodecs = supportedCodecs;
      this.comfortNoiseSupport = comfortNoiseSupport;
    }
  }
  /** Default constructor */
  public SupportedAudioStreamConfigurationCharacteristic(
      Supplier<CompletableFuture<SupportedAudioStreamConfiguration>> getter) {
    super(
        "00000115-0000-1000-8000-0026BB765291",
        "Supported Audio Stream Configuration",
        true,
        false);
    this.getter = getter;
  }

  private final Supplier<CompletableFuture<SupportedAudioStreamConfiguration>> getter;

  @Override
  protected void setValue(String value) throws Exception {}

  @Override
  protected CompletableFuture<String> getValue() {
    return getter
        .get()
        .thenApply(
            config -> {
              TypeLengthValueUtils.Encoder codecs = TypeLengthValueUtils.getEncoder();
              codecs
                  .addAllEmptySeparated((short) 0x01, config.supportedCodecs)
                  .add((short) 0x02, config.comfortNoiseSupport ? (short) 0x01 : (short) 0x00);

              return Base64.toBase64String(codecs.toByteArray());
            });
  }
}
