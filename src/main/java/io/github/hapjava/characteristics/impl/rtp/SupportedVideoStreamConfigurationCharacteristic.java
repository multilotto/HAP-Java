package io.github.hapjava.characteristics.impl.rtp;

import io.github.hapjava.characteristics.Base64TlvCharacteristic;
import io.github.hapjava.characteristics.impl.rtp.types.VideoAttributesTypes;
import io.github.hapjava.characteristics.impl.rtp.types.VideoTypes;
import io.github.hapjava.server.impl.pairing.TypeLengthValueUtils;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;
import org.bouncycastle.util.encoders.Base64;

public class SupportedVideoStreamConfigurationCharacteristic extends Base64TlvCharacteristic {
  public static class SupportedVideoStreamConfiguration {
    private VideoTypes videoTypes;
    private List<VideoAttributesTypes> videoAttributesTypes;

    public SupportedVideoStreamConfiguration(
        VideoTypes videoTypes, List<VideoAttributesTypes> videoAttributesTypes) {
      this.videoTypes = videoTypes;
      this.videoAttributesTypes = videoAttributesTypes;
    }
  }

  /** Default constructor */
  public SupportedVideoStreamConfigurationCharacteristic(
      Supplier<CompletableFuture<SupportedVideoStreamConfiguration>> getter) {
    super(
        "00000114-0000-1000-8000-0026BB765291",
        "Supported Video Stream Configuration",
        true,
        false);
    this.getter = getter;
  }

  Supplier<CompletableFuture<SupportedVideoStreamConfiguration>> getter;

  @Override
  protected void setValue(String value) throws Exception {}

  private static void writeBytes(ByteArrayOutputStream baos, byte[] bytes) {
    baos.write(bytes, 0, bytes.length);
  }

  @Override
  protected CompletableFuture<String> getValue() {
    return getter
        .get()
        .thenApply(
            config -> {
              ByteArrayOutputStream baos = new ByteArrayOutputStream();
              writeBytes(baos, config.videoTypes.encodeTLV());

              TypeLengthValueUtils.Encoder videoAttrsTLV = TypeLengthValueUtils.getEncoder();
              for (VideoAttributesTypes vat : config.videoAttributesTypes) {
                videoAttrsTLV.add(VideoTypes.ATTRIBUTES, vat.encodeTLV());
              }
              writeBytes(baos, videoAttrsTLV.toByteArray());

              byte[] ret =
                  TypeLengthValueUtils.getEncoder()
                      .add((short) 0x01, baos.toByteArray())
                      .toByteArray();

              return Base64.toBase64String(ret);
            });
  }
}
