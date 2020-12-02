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

import io.github.hapjava.accessories.ThermostatAccessory;
import io.github.hapjava.characteristics.HomekitCharacteristicChangeCallback;
import io.github.hapjava.characteristics.impl.thermostat.CurrentHeatingCoolingStateEnum;
import io.github.hapjava.characteristics.impl.thermostat.TargetHeatingCoolingStateEnum;
import io.github.hapjava.characteristics.impl.thermostat.TemperatureDisplayUnitEnum;
import java.util.concurrent.CompletableFuture;

/** @author paluch */
public class MockThermostat implements ThermostatAccessory {

  @Override
  public CompletableFuture<Double> getCurrentTemperature() {
    return CompletableFuture.completedFuture(22.5);
  }

  @Override
  public CompletableFuture<Double> getTargetTemperature() {
    return CompletableFuture.completedFuture(22.5);
  }

  @Override
  public void setTargetTemperature(Double value) throws Exception {}

  @Override
  public CompletableFuture<CurrentHeatingCoolingStateEnum> getCurrentState() {
    return CompletableFuture.completedFuture(CurrentHeatingCoolingStateEnum.OFF);
  }

  @Override
  public void setTargetState(TargetHeatingCoolingStateEnum mode) throws Exception {}

  @Override
  public CompletableFuture<TargetHeatingCoolingStateEnum> getTargetState() {
    return CompletableFuture.completedFuture(TargetHeatingCoolingStateEnum.OFF);
  }

  @Override
  public CompletableFuture<TemperatureDisplayUnitEnum> getTemperatureDisplayUnit() {
    return CompletableFuture.completedFuture(TemperatureDisplayUnitEnum.CELSIUS);
  }

  @Override
  public void setTemperatureDisplayUnit(TemperatureDisplayUnitEnum value) throws Exception {}

  @Override
  public void subscribeTargetState(HomekitCharacteristicChangeCallback callback) {}

  @Override
  public void subscribeTargetTemperature(HomekitCharacteristicChangeCallback callback) {}

  @Override
  public void subscribeTemperatureDisplayUnit(HomekitCharacteristicChangeCallback callback) {}

  @Override
  public void subscribeCurrentTemperature(HomekitCharacteristicChangeCallback callback) {}

  @Override
  public void subscribeCurrentState(HomekitCharacteristicChangeCallback callback) {}

  @Override
  public void unsubscribeCurrentState() {}

  @Override
  public void unsubscribeTargetState() {}

  @Override
  public void unsubscribeCurrentTemperature() {}

  @Override
  public void unsubscribeTemperatureDisplayUnit() {}

  @Override
  public void unsubscribeTargetTemperature() {}

  @Override
  public int getId() {
    return 300;
  }

  @Override
  public CompletableFuture<String> getName() {
    return CompletableFuture.completedFuture("Thermostat");
  }

  @Override
  public void identify() {
    System.out.println("Thermostat");
  }

  @Override
  public CompletableFuture<String> getSerialNumber() {
    return CompletableFuture.completedFuture("Test Thermostat");
  }

  @Override
  public CompletableFuture<String> getModel() {
    return CompletableFuture.completedFuture("Thermostat Model");
  }

  @Override
  public CompletableFuture<String> getManufacturer() {
    return CompletableFuture.completedFuture("Test Thermostat");
  }

  @Override
  public CompletableFuture<String> getFirmwareRevision() {
    return CompletableFuture.completedFuture("Test Thermostat Firmware");
  }
}
