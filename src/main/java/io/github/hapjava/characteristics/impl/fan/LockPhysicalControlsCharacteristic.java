package io.github.hapjava.characteristics.impl.fan;

import io.github.hapjava.characteristics.EventableCharacteristic;
import io.github.hapjava.characteristics.ExceptionalConsumer;
import io.github.hapjava.characteristics.HomekitCharacteristicChangeCallback;
import io.github.hapjava.characteristics.impl.base.EnumCharacteristic;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Supplier;

/** This characteristic describes physical controls on an accessory (eg. child lock). */
public class LockPhysicalControlsCharacteristic extends EnumCharacteristic<LockPhysicalControlsEnum>
    implements EventableCharacteristic {

  public LockPhysicalControlsCharacteristic(
      Supplier<CompletableFuture<LockPhysicalControlsEnum>> getter,
      ExceptionalConsumer<LockPhysicalControlsEnum> setter,
      Consumer<HomekitCharacteristicChangeCallback> subscriber,
      Runnable unsubscriber) {
    super(
        "000000A7-0000-1000-8000-0026BB765291",
        "Physical Locks",
        LockPhysicalControlsEnum.values(),
        Optional.of(getter),
        Optional.of(setter),
        Optional.of(subscriber),
        Optional.of(unsubscriber));
  }
}
