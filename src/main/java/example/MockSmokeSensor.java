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

import io.github.hapjava.accessories.SmokeSensorAccessory;
import io.github.hapjava.characteristics.HomekitCharacteristicChangeCallback;
import io.github.hapjava.characteristics.impl.smokesensor.SmokeDetectedStateEnum;
import java.util.concurrent.CompletableFuture;

/** @author paluch */
public class MockSmokeSensor implements SmokeSensorAccessory {

  @Override
  public CompletableFuture<SmokeDetectedStateEnum> getSmokeDetectedState() {
    return CompletableFuture.completedFuture(SmokeDetectedStateEnum.NOT_DETECTED);
  }

  @Override
  public void subscribeSmokeDetectedState(HomekitCharacteristicChangeCallback callback) {}

  @Override
  public void unsubscribeSmokeDetectedState() {}

  @Override
  public int getId() {
    return 181;
  }

  @Override
  public CompletableFuture<String> getName() {
    return CompletableFuture.completedFuture("Test Smoke Name");
  }

  @Override
  public void identify() {
    System.out.println("Smoke");
  }

  @Override
  public CompletableFuture<String> getSerialNumber() {
    return CompletableFuture.completedFuture("Test Smoke SN");
  }

  @Override
  public CompletableFuture<String> getModel() {
    return CompletableFuture.completedFuture("Smoke Model");
  }

  @Override
  public CompletableFuture<String> getManufacturer() {
    return CompletableFuture.completedFuture("Test Smoke Manufacturer");
  }

  @Override
  public CompletableFuture<String> getFirmwareRevision() {
    return CompletableFuture.completedFuture("Test Smoke Firmware");
  }
}
