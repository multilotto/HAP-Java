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

import io.github.hapjava.accessories.DoorbellAccessory;
import io.github.hapjava.characteristics.HomekitCharacteristicChangeCallback;
import io.github.hapjava.characteristics.impl.common.ProgrammableSwitchEnum;
import java.util.Timer;
import java.util.concurrent.CompletableFuture;

/** @author paluch */
public class MockDoorbell implements DoorbellAccessory {

  Timer timer = new Timer();

  public MockDoorbell() {
    /*
        timer.schedule(
            new TimerTask() {
              @Override
              public void run() {
                for (int i = 0; i < 10; i++) {
                  System.out.println("-----> DING DONG!!!!!");

                  if (callback != null) {
                    callback.changed();
                    if (state == null) state = ProgrammableSwitchEnum.SINGLE_PRESS;
                    else if (state == ProgrammableSwitchEnum.SINGLE_PRESS)
                      state = ProgrammableSwitchEnum.DOUBLE_PRESS;
                    else if (state == ProgrammableSwitchEnum.DOUBLE_PRESS)
                      state = ProgrammableSwitchEnum.LONG_PRESS;
                    else state = null;

                    System.out.println("-----> DING DONG!!!!! call");
                  }
                  try {
                    Thread.sleep(10000);
                  } catch (InterruptedException ex) {
                    Logger.getLogger(MockDoorbell.class.getName()).log(Level.SEVERE, null, ex);
                  }
                }
              }
            },
            30000);
    */
  }

  private HomekitCharacteristicChangeCallback callback;
  private ProgrammableSwitchEnum state = null;

  @Override
  public CompletableFuture<ProgrammableSwitchEnum> getSwitchEvent() {
    System.out.println("-----> getSwitchEvent " + ((state == null) ? "NULL" : state));
    return CompletableFuture.completedFuture(state);
  }

  @Override
  public void subscribeSwitchEvent(HomekitCharacteristicChangeCallback callback) {
    this.callback = callback;
  }

  @Override
  public void unsubscribeSwitchEvent() {
    this.callback = null;
  }

  @Override
  public int getId() {
    return 1000;
  }

  @Override
  public CompletableFuture<String> getName() {
    return CompletableFuture.completedFuture("Doorbell Test");
  }

  @Override
  public void identify() {
    System.out.println("Doorbell");
  }

  @Override
  public CompletableFuture<String> getSerialNumber() {
    return CompletableFuture.completedFuture("Test DoorbellSN");
  }

  @Override
  public CompletableFuture<String> getModel() {
    return CompletableFuture.completedFuture("TestDoorbell Model");
  }

  @Override
  public CompletableFuture<String> getManufacturer() {
    return CompletableFuture.completedFuture("Test DoorbellManufacturer");
  }

  @Override
  public CompletableFuture<String> getFirmwareRevision() {
    return CompletableFuture.completedFuture("Test Doorbell Firmware");
  }
}
