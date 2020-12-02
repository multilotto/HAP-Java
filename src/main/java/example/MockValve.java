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

import io.github.hapjava.accessories.ValveAccessory;
import io.github.hapjava.characteristics.HomekitCharacteristicChangeCallback;
import io.github.hapjava.characteristics.impl.common.ActiveEnum;
import io.github.hapjava.characteristics.impl.common.InUseEnum;
import io.github.hapjava.characteristics.impl.valve.ValveTypeEnum;
import java.util.concurrent.CompletableFuture;

/** @author paluch */
public class MockValve implements ValveAccessory {
  private ActiveEnum active = ActiveEnum.ACTIVE;
  private HomekitCharacteristicChangeCallback ac;
  private InUseEnum status = InUseEnum.NOT_IN_USE;
  private HomekitCharacteristicChangeCallback uc;

  @Override
  public CompletableFuture<ActiveEnum> getValveActive() {
    System.out.println("----> getValveActive " + active);
    return CompletableFuture.completedFuture(active);
  }

  @Override
  public CompletableFuture<Void> setValveActive(ActiveEnum active) throws Exception {
    this.active = active;
    System.out.println("----> setValveActive " + active);

    if (active == ActiveEnum.ACTIVE) status = InUseEnum.IN_USE;
    else status = InUseEnum.NOT_IN_USE;

    if (ac != null) ac.changed();
    if (uc != null) uc.changed();
    return CompletableFuture.completedFuture(null);
  }

  @Override
  public CompletableFuture<InUseEnum> getValveInUse() {
    // return CompletableFuture.completedFuture(null);
    System.out.println("----> getValveInUse() ");
    return CompletableFuture.completedFuture(status);
  }

  @Override
  public CompletableFuture<ValveTypeEnum> getValveType() {
    System.out.println("----> getValveType() ");
    return CompletableFuture.completedFuture(ValveTypeEnum.SHOWER);
  }

  @Override
  public void subscribeValveActive(HomekitCharacteristicChangeCallback callback) {
    ac = callback;
  }

  @Override
  public void unsubscribeValveActive() {
    ac = null;
  }

  @Override
  public void subscribeValveInUse(HomekitCharacteristicChangeCallback callback) {
    uc = callback;
  }

  @Override
  public void unsubscribeValveInUse() {
    uc = null;
  }

  @Override
  public void subscribeValveType(HomekitCharacteristicChangeCallback callback) {}

  @Override
  public void unsubscribeValveType() {}

  @Override
  public int getId() {
    return 3;
  }

  @Override
  public CompletableFuture<String> getName() {
    return CompletableFuture.completedFuture("Valve");
  }

  @Override
  public void identify() {
    System.out.println("Valve");
  }

  @Override
  public CompletableFuture<String> getSerialNumber() {
    return CompletableFuture.completedFuture("Test ValveSN");
  }

  @Override
  public CompletableFuture<String> getModel() {
    return CompletableFuture.completedFuture("TestValve Model");
  }

  @Override
  public CompletableFuture<String> getManufacturer() {
    return CompletableFuture.completedFuture("Test ValveManufacturer");
  }

  @Override
  public CompletableFuture<String> getFirmwareRevision() {
    return CompletableFuture.completedFuture("Test Valve Firmware");
  }
}
