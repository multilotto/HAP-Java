/*
 * The MIT License
 *
 * Copyright 2020 paluch.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package example;

import io.github.hapjava.accessories.IrrigationSystemAccessory;
import io.github.hapjava.accessories.optionalcharacteristic.AccessoryWithRemainingDuration;
import io.github.hapjava.accessories.optionalcharacteristic.AccessoryWithStatusFault;
import io.github.hapjava.characteristics.HomekitCharacteristicChangeCallback;
import io.github.hapjava.characteristics.impl.common.ActiveEnum;
import io.github.hapjava.characteristics.impl.common.InUseEnum;
import io.github.hapjava.characteristics.impl.common.ProgramModeEnum;
import io.github.hapjava.characteristics.impl.common.StatusFaultEnum;
import io.github.hapjava.services.Service;
import io.github.hapjava.services.impl.IrrigationSystemService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/** @author paluch */
public class MockIrrigationSystem
    implements IrrigationSystemAccessory, AccessoryWithStatusFault, AccessoryWithRemainingDuration {

  private final MockSwitch sw;
  private List<Service> mylinked = new ArrayList<>();

  MockIrrigationSystem() {
    sw = new MockSwitch(1010101);
    mylinked.addAll(0, sw.getServices());
  }

  private ActiveEnum state = ActiveEnum.INACTIVE;
  private HomekitCharacteristicChangeCallback uc;
  private HomekitCharacteristicChangeCallback ac;
  private HomekitCharacteristicChangeCallback pc;

  @Override
  public CompletableFuture<ActiveEnum> getActive() {
    System.out.println("Irrigation - getActive " + state);
    return CompletableFuture.completedFuture(ActiveEnum.ACTIVE);
  }

  @Override
  public CompletableFuture<Void> setActive(ActiveEnum active) throws Exception {
    state = active;
    System.out.println("Irrigation - setActive " + state);
    // if (ac != null) ac.changed();
    return CompletableFuture.completedFuture(null);
  }

  @Override
  public CompletableFuture<ProgramModeEnum> getProgramMode() {
    System.out.println("Irrigation - getProgramMode ");
    return CompletableFuture.completedFuture(ProgramModeEnum.SCHEDULED_MANUAL);
  }

  @Override
  public CompletableFuture<InUseEnum> getInUse() {
    System.out.println("Irrigation - getInUse ");
    return CompletableFuture.completedFuture(InUseEnum.IN_USE);
  }

  @Override
  public void subscribeInUse(HomekitCharacteristicChangeCallback callback) {
    uc = callback;
  }

  @Override
  public void unsubscribeInUse() {
    uc = null;
  }

  @Override
  public void subscribeActive(HomekitCharacteristicChangeCallback callback) {
    ac = callback;
  }

  @Override
  public void unsubscribeActive() {
    ac = null;
  }

  @Override
  public void subscribeProgramMode(HomekitCharacteristicChangeCallback callback) {
    pc = callback;
  }

  @Override
  public void unsubscribeProgramMode() {
    pc = null;
  }

  @Override
  public int getId() {
    return 1400;
  }

  @Override
  public CompletableFuture<String> getName() {
    return CompletableFuture.completedFuture("Test Irrigation");
  }

  @Override
  public void identify() {
    System.out.println("Irrigation");
  }

  @Override
  public CompletableFuture<String> getSerialNumber() {
    return CompletableFuture.completedFuture("Test Irrigation SN");
  }

  @Override
  public CompletableFuture<String> getModel() {
    return CompletableFuture.completedFuture("Irrigation Model");
  }

  @Override
  public CompletableFuture<String> getManufacturer() {
    return CompletableFuture.completedFuture("Test Irrigation Manufacturer");
  }

  @Override
  public CompletableFuture<String> getFirmwareRevision() {
    return CompletableFuture.completedFuture("Test Irrigation Firmware");
  }

  @Override
  public CompletableFuture<StatusFaultEnum> getStatusFault() {
    return CompletableFuture.completedFuture(StatusFaultEnum.NO_FAULT);
  }

  @Override
  public void subscribeStatusFault(HomekitCharacteristicChangeCallback callback) {}

  @Override
  public void unsubscribeStatusFault() {}

  @Override
  public CompletableFuture<Integer> getRemainingDuration() {
    return CompletableFuture.completedFuture(1);
  }

  @Override
  public void subscribeRemainingDuration(HomekitCharacteristicChangeCallback callback) {}

  @Override
  public void unsubscribeRemainingDuration() {}

  @Override
  public Collection<Service> getServices() {
    return Collections.singleton(
        new IrrigationSystemService(this) {
          @Override
          public List<Service> getLinkedServices() {
            return mylinked;
          }
        });
  }
}
