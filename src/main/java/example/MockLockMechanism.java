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

import io.github.hapjava.accessories.LockMechanismAccessory;
import io.github.hapjava.characteristics.HomekitCharacteristicChangeCallback;
import io.github.hapjava.characteristics.impl.lock.LockCurrentStateEnum;
import io.github.hapjava.characteristics.impl.lock.LockTargetStateEnum;
import java.util.concurrent.CompletableFuture;

/** @author paluch */
public class MockLockMechanism implements LockMechanismAccessory {

  @Override
  public CompletableFuture<LockCurrentStateEnum> getLockCurrentState() {
    return CompletableFuture.completedFuture(LockCurrentStateEnum.SECURED);
  }

  @Override
  public CompletableFuture<LockTargetStateEnum> getLockTargetState() {
    return CompletableFuture.completedFuture(LockTargetStateEnum.SECURED);
  }

  @Override
  public CompletableFuture<Void> setLockTargetState(LockTargetStateEnum state) {
    return CompletableFuture.completedFuture(null);
  }

  @Override
  public void subscribeLockCurrentState(HomekitCharacteristicChangeCallback callback) {}

  @Override
  public void unsubscribeLockCurrentState() {}

  @Override
  public void subscribeLockTargetState(HomekitCharacteristicChangeCallback callback) {}

  @Override
  public void unsubscribeLockTargetState() {}

  @Override
  public int getId() {
    return 20;
  }

  @Override
  public CompletableFuture<String> getName() {
    return CompletableFuture.completedFuture("Test Lock Name");
  }

  @Override
  public void identify() {
    System.out.println("Lock");
  }

  @Override
  public CompletableFuture<String> getSerialNumber() {
    return CompletableFuture.completedFuture("Test Lock SN");
  }

  @Override
  public CompletableFuture<String> getModel() {
    return CompletableFuture.completedFuture("Lock Model");
  }

  @Override
  public CompletableFuture<String> getManufacturer() {
    return CompletableFuture.completedFuture("Test Lock Manufacturer");
  }

  @Override
  public CompletableFuture<String> getFirmwareRevision() {
    return CompletableFuture.completedFuture("Test Lock Firmware");
  }
}
