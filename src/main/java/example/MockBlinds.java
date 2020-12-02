package example;

import io.github.hapjava.accessories.WindowCoveringAccessory;
import io.github.hapjava.accessories.optionalcharacteristic.AccessoryWithCurrentHorizontalTilting;
import io.github.hapjava.accessories.optionalcharacteristic.AccessoryWithHoldPosition;
import io.github.hapjava.accessories.optionalcharacteristic.AccessoryWithTargetHorizontalTilting;
import io.github.hapjava.characteristics.HomekitCharacteristicChangeCallback;
import io.github.hapjava.characteristics.impl.windowcovering.PositionStateEnum;
import java.util.concurrent.CompletableFuture;

/** @author paluch */
public class MockBlinds
    implements WindowCoveringAccessory,
        AccessoryWithCurrentHorizontalTilting,
        AccessoryWithTargetHorizontalTilting,
        AccessoryWithHoldPosition {
  private HomekitCharacteristicChangeCallback subscribeStateCallback = null;
  private HomekitCharacteristicChangeCallback subscribeCurrentCallback = null;
  private HomekitCharacteristicChangeCallback subscribeTargetCallback = null;
  private HomekitCharacteristicChangeCallback subscribeCurrentAngleCallback = null;
  private HomekitCharacteristicChangeCallback subscribeTargetAngleCallback = null;
  private int m_angle = 90;
  private int m_position = 0;
  private int m_tangle = 90;
  private int m_tposition = 0;
  PositionStateEnum m_state = PositionStateEnum.STOPPED;

  public MockBlinds() {}

  private void updatePozition() {
    m_state = PositionStateEnum.STOPPED;
    m_position = getPozition();
    m_tposition = m_position;
    if (subscribeCurrentCallback != null) subscribeCurrentCallback.changed();
    if (subscribeTargetCallback != null) subscribeTargetCallback.changed();
    if (subscribeStateCallback != null) subscribeStateCallback.changed();
  }

  private void updateAngle() {
    m_state = PositionStateEnum.STOPPED;
    m_angle = getAngle();
    m_tangle = m_angle;
    if (subscribeCurrentAngleCallback != null) subscribeCurrentAngleCallback.changed();
    if (subscribeTargetAngleCallback != null) subscribeTargetAngleCallback.changed();
    if (subscribeStateCallback != null) subscribeStateCallback.changed();
  }

  @Override
  public CompletableFuture<Void> setTargetPosition(int i) throws Exception {

    m_tposition = i;
    if (subscribeTargetCallback != null) subscribeTargetCallback.changed();
    if (m_tposition > m_position) m_state = PositionStateEnum.INCREASING;
    else m_state = PositionStateEnum.DECREASING;
    if (subscribeStateCallback != null) subscribeStateCallback.changed();
    byte x[] = new byte[1];
    x[0] = (byte) (((100 - m_tposition) * 255) / 100);

    return CompletableFuture.completedFuture(null);
  }

  @Override
  public CompletableFuture<Void> setTargetHorizontalTiltAngle(int i) throws Exception {
    m_tangle = i;
    if (subscribeTargetAngleCallback != null) subscribeTargetAngleCallback.changed();

    if (m_tangle > m_angle) m_state = PositionStateEnum.INCREASING;
    else m_state = PositionStateEnum.DECREASING;
    if (subscribeStateCallback != null) subscribeStateCallback.changed();

    return CompletableFuture.completedFuture(null);
  }

  private int getAngle() {
    return m_angle;
  }

  @Override
  public CompletableFuture<Integer> getCurrentHorizontalTiltAngle() {
    return CompletableFuture.completedFuture(m_angle);
  }

  @Override
  public CompletableFuture<Integer> getTargetHorizontalTiltAngle() {
    return CompletableFuture.completedFuture(m_tangle);
  }

  private int getPozition() {
    return m_position;
  }

  @Override
  public CompletableFuture<Integer> getCurrentPosition() {
    return CompletableFuture.completedFuture(m_position);
  }

  @Override
  public CompletableFuture<Integer> getTargetPosition() {
    return CompletableFuture.completedFuture(m_tposition);
  }

  @Override
  public CompletableFuture<PositionStateEnum> getPositionState() {
    return CompletableFuture.completedFuture(m_state);
  }

  @Override
  public void subscribeCurrentPosition(HomekitCharacteristicChangeCallback hccc) {
    subscribeCurrentCallback = hccc;
  }

  @Override
  public void subscribeTargetPosition(HomekitCharacteristicChangeCallback hccc) {
    subscribeTargetCallback = hccc;
  }

  @Override
  public void subscribePositionState(HomekitCharacteristicChangeCallback hccc) {
    subscribeStateCallback = hccc;
  }

  @Override
  public void unsubscribeCurrentPosition() {
    subscribeCurrentCallback = null;
  }

  @Override
  public void unsubscribeTargetPosition() {
    subscribeTargetCallback = null;
  }

  @Override
  public void unsubscribePositionState() {
    subscribeStateCallback = null;
  }

  @Override
  public void subscribeCurrentHorizontalTiltAngle(HomekitCharacteristicChangeCallback hccc) {
    subscribeCurrentAngleCallback = hccc;
  }

  @Override
  public void unsubscribeCurrentHorizontalTiltAngle() {
    subscribeCurrentAngleCallback = null;
  }

  @Override
  public void subscribeTargetHorizontalTiltAngle(HomekitCharacteristicChangeCallback hccc) {
    subscribeTargetAngleCallback = hccc;
  }

  @Override
  public void unsubscribeTargetHorizontalTiltAngle() {
    subscribeTargetAngleCallback = null;
  }

  @Override
  public int getId() {
    return 1324;
  }

  @Override
  public CompletableFuture<String> getName() {
    return CompletableFuture.completedFuture("Test Blinds");
  }

  @Override
  public void identify() {
    System.out.println("Blinds");
  }

  @Override
  public CompletableFuture<String> getSerialNumber() {
    return CompletableFuture.completedFuture("1");
  }

  @Override
  public CompletableFuture<String> getModel() {
    return CompletableFuture.completedFuture("Blinds");
  }

  @Override
  public CompletableFuture<String> getManufacturer() {
    return CompletableFuture.completedFuture("Paluch");
  }

  @Override
  public CompletableFuture<String> getFirmwareRevision() {
    return CompletableFuture.completedFuture("Test Blinds Firmware");
  }

  @Override
  public CompletableFuture<Void> setHoldPosition(boolean bln) throws Exception {
    System.out.println("BLINDS - setHoldPosition " + bln);
    return CompletableFuture.completedFuture(null);
  }
}
