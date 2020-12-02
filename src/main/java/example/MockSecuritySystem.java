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

import io.github.hapjava.accessories.SecuritySystemAccessory;
import io.github.hapjava.characteristics.HomekitCharacteristicChangeCallback;
import io.github.hapjava.characteristics.impl.securitysystem.CurrentSecuritySystemStateEnum;
import io.github.hapjava.characteristics.impl.securitysystem.TargetSecuritySystemStateEnum;
import java.util.concurrent.CompletableFuture;

/** @author paluch */
public class MockSecuritySystem implements SecuritySystemAccessory {

  private TargetSecuritySystemStateEnum tstate = TargetSecuritySystemStateEnum.DISARM;
  private CurrentSecuritySystemStateEnum cstate = CurrentSecuritySystemStateEnum.DISARMED;
  private HomekitCharacteristicChangeCallback tc;
  private HomekitCharacteristicChangeCallback cc;

  @Override
  public CompletableFuture<CurrentSecuritySystemStateEnum> getCurrentSecuritySystemState() {
    if (tstate != null) {
      switch (tstate) {
        case STAY_ARM:
          cstate = CurrentSecuritySystemStateEnum.STAY_ARM;
          break;
        case AWAY_ARM:
          cstate = CurrentSecuritySystemStateEnum.AWAY_ARM;
          break;
        case NIGHT_ARM:
          cstate = CurrentSecuritySystemStateEnum.NIGHT_ARM;
          break;
        case DISARM:
          cstate = CurrentSecuritySystemStateEnum.DISARMED;
          break;
      }
    }
    System.out.println("Get alarm state -------> " + cstate);
    return CompletableFuture.completedFuture(cstate);
  }

  @Override
  public void setTargetSecuritySystemState(TargetSecuritySystemStateEnum state) throws Exception {
    this.tstate = state;
    System.out.println("Set alarm target -------> " + state);
    if (tc != null) tc.changed();
    if (cc != null) cc.changed();
  }

  @Override
  public CompletableFuture<TargetSecuritySystemStateEnum> getTargetSecuritySystemState() {
    System.out.println("Get alarm target -------> " + tstate);
    return CompletableFuture.completedFuture(tstate);
  }

  @Override
  public void subscribeTargetSecuritySystemState(HomekitCharacteristicChangeCallback callback) {
    tc = callback;
  }

  @Override
  public void subscribeCurrentSecuritySystemState(HomekitCharacteristicChangeCallback callback) {
    cc = callback;
  }

  @Override
  public void unsubscribeCurrentSecuritySystemState() {
    cc = null;
  }

  @Override
  public void unsubscribeTargetSecuritySystemState() {
    tc = null;
  }

  @Override
  public int getId() {
    return 15;
  }

  @Override
  public CompletableFuture<String> getName() {
    return CompletableFuture.completedFuture("Test Alarm Name");
  }

  @Override
  public void identify() {
    System.out.println("Alarm");
  }

  @Override
  public CompletableFuture<String> getSerialNumber() {
    return CompletableFuture.completedFuture("Test AlarmSN");
  }

  @Override
  public CompletableFuture<String> getModel() {
    return CompletableFuture.completedFuture("Alarm Model");
  }

  @Override
  public CompletableFuture<String> getManufacturer() {
    return CompletableFuture.completedFuture("Test Alarm Manufacturer");
  }

  @Override
  public CompletableFuture<String> getFirmwareRevision() {
    return CompletableFuture.completedFuture("Test Alarm Firmware");
  }
}
