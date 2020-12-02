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

import io.github.hapjava.accessories.OutletAccessory;
import io.github.hapjava.characteristics.HomekitCharacteristicChangeCallback;
import java.util.concurrent.CompletableFuture;

/** @author paluch */
public class MockOutlet implements OutletAccessory {

  @Override
  public CompletableFuture<Boolean> getPowerState() {
    return CompletableFuture.completedFuture(false);
  }

  @Override
  public CompletableFuture<Boolean> getOutletInUse() {
    return CompletableFuture.completedFuture(false);
  }

  @Override
  public CompletableFuture<Void> setPowerState(boolean state) throws Exception {
    return CompletableFuture.completedFuture(null);
  }

  @Override
  public void subscribePowerState(HomekitCharacteristicChangeCallback callback) {}

  @Override
  public void subscribeOutletInUse(HomekitCharacteristicChangeCallback callback) {}

  @Override
  public void unsubscribePowerState() {}

  @Override
  public void unsubscribeOutletInUse() {}

  @Override
  public int getId() {
    return 16;
  }

  @Override
  public CompletableFuture<String> getName() {
    return CompletableFuture.completedFuture("Test Outlet Name");
  }

  @Override
  public void identify() {
    System.out.println("Outlet");
  }

  @Override
  public CompletableFuture<String> getSerialNumber() {
    return CompletableFuture.completedFuture("Test Outlet SN");
  }

  @Override
  public CompletableFuture<String> getModel() {
    return CompletableFuture.completedFuture("Outlet Model");
  }

  @Override
  public CompletableFuture<String> getManufacturer() {
    return CompletableFuture.completedFuture("Test Outlet Manufacturer");
  }

  @Override
  public CompletableFuture<String> getFirmwareRevision() {
    return CompletableFuture.completedFuture("Test Outlet Firmware");
  }
}
