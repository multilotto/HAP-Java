package example;

import io.github.hapjava.server.impl.HomekitRoot;
import io.github.hapjava.server.impl.HomekitServer;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

  private static final int PORT = 9123;

  public static void main(String[] args) {
    try {
      InetAddress inetAddress = InetAddress.getLocalHost();
      System.out.println("IP Address:- " + inetAddress.getHostAddress());
      System.out.println("Host Name:- " + inetAddress.getHostName());
      Enumeration en = NetworkInterface.getNetworkInterfaces();
      while (en.hasMoreElements()) {
        NetworkInterface n = (NetworkInterface) en.nextElement();
        Enumeration ee = n.getInetAddresses();
        while (ee.hasMoreElements()) {
          InetAddress i = (InetAddress) ee.nextElement();
          System.out.println(i.getHostAddress());
        }
      }
      //      System.exit(1);

      Logger root = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
      // root.setLevel(Level.ALL);
      Logger logger = LoggerFactory.getLogger(Main.class);
      System.out.println("DEBUG: " + logger.isDebugEnabled());

      File authFile = new File("auth3-state.bin");
      MockAuthInfo mockAuth;
      if (authFile.exists()) {
        FileInputStream fileInputStream = new FileInputStream(authFile);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        try {
          System.out.println("Using persisted auth");
          AuthState authState = (AuthState) objectInputStream.readObject();
          mockAuth = new MockAuthInfo(authState);
        } finally {
          objectInputStream.close();
        }
      } else {
        mockAuth = new MockAuthInfo();
      }

      HomekitServer homekit = new HomekitServer(InetAddress.getByName("192.168.1.112"), PORT, 20);
      HomekitRoot bridge =
          homekit.createBridge(
//              mockAuth, "Paluch 3 Bridge", "PaluchBridge, Inc.", "G6", "133abe224", "1.1", "1.2");
              mockAuth, "Paluch 3 Bridge", "PaluchBridge, Inc.", "G6", "133ABC114", "1.1", "1.2");

      mockAuth.onChange(
          state -> {
            try {
              System.out.println("State has changed! Writing");
              FileOutputStream fileOutputStream = new FileOutputStream(authFile);
              ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
              objectOutputStream.writeObject(state);
              objectOutputStream.flush();
              objectOutputStream.close();
            } catch (IOException e) {
              e.printStackTrace();
            }
          });
      bridge.addAccessory(new MockBulb());

      bridge.addAccessory(new MockValve());

      bridge.addAccessory(new MockDoor());

      bridge.addAccessory(new MockDoorbell());

      bridge.addAccessory(new MockSwitch(14));

      bridge.addAccessory(new MockSecuritySystem());

      bridge.addAccessory(new MockOutlet());

      bridge.addAccessory(new MockLockMechanism());

      bridge.addAccessory(new MockGarageDoorOpener());

      bridge.addAccessory(new MockLeakSensor());

      bridge.addAccessory(new MockContactSensor());

      bridge.addAccessory(new MockThermostat());

      bridge.addAccessory(new MockSmokeSensor());

      bridge.addAccessory(new MockEasyThermostat());

      bridge.addAccessory(new MockMotionSensor());

      bridge.addAccessory(new MockIrrigationSystem());

      bridge.addAccessory(new MockOccupancySensor());

      bridge.addAccessory(new MockCarbonMonoxideSensor());

      bridge.addAccessory(new MockCarbonDioxideSensor());

      bridge.addAccessory(new MockBlinds());

      bridge.start();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
