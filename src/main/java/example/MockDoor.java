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

import io.github.hapjava.accessories.DoorAccessory;
import io.github.hapjava.characteristics.HomekitCharacteristicChangeCallback;
import io.github.hapjava.characteristics.impl.windowcovering.PositionStateEnum;
import java.util.concurrent.CompletableFuture;

/** @author paluch */
public class MockDoor implements DoorAccessory {

  private Integer poz = 1;
  private HomekitCharacteristicChangeCallback pc;
  private HomekitCharacteristicChangeCallback cc;
  private HomekitCharacteristicChangeCallback tc;

  @Override
  public CompletableFuture<PositionStateEnum> getPositionState() {
    return CompletableFuture.completedFuture(PositionStateEnum.STOPPED);
  }

  @Override
  public CompletableFuture<Integer> getCurrentPosition() {
    return CompletableFuture.completedFuture(poz);
  }

  @Override
  public CompletableFuture<Integer> getTargetPosition() {
    return CompletableFuture.completedFuture(poz);
  }

  @Override
  public CompletableFuture<Void> setTargetPosition(Integer position) throws Exception {

    System.out.println("setTargetPosition( ) " + position);

    poz = position;
    if (tc != null) tc.changed();
    if (cc != null) cc.changed();
    if (pc != null) pc.changed();
    return CompletableFuture.completedFuture(null);
  }

  @Override
  public void subscribePositionState(HomekitCharacteristicChangeCallback callback) {
    pc = callback;
  }

  @Override
  public void subscribeCurrentPosition(HomekitCharacteristicChangeCallback callback) {
    cc = callback;
  }

  @Override
  public void subscribeTargetPosition(HomekitCharacteristicChangeCallback callback) {
    tc = callback;
  }

  @Override
  public void unsubscribePositionState() {
    pc = null;
  }

  @Override
  public void unsubscribeCurrentPosition() {
    cc = null;
  }

  @Override
  public void unsubscribeTargetPosition() {
    tc = null;
  }

  @Override
  public int getId() {
    return 4;
  }

  @Override
  public CompletableFuture<String> getName() {
    return CompletableFuture.completedFuture("Test Door Name");
  }

  @Override
  public void identify() {
    System.out.println("Door");
  }

  @Override
  public CompletableFuture<String> getSerialNumber() {
    return CompletableFuture.completedFuture("Test DoorSN");
  }

  @Override
  public CompletableFuture<String> getModel() {
    return CompletableFuture.completedFuture("TestDoor Model");
  }

  @Override
  public CompletableFuture<String> getManufacturer() {
    return CompletableFuture.completedFuture("Test DoorManufacturer");
  }

  @Override
  public CompletableFuture<String> getFirmwareRevision() {
    return CompletableFuture.completedFuture("Test Door Firmware");
  }
}
