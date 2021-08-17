package utility;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class GameUtility {

  public static long getTimeInSeconds(long timePassed) {
    return (long) (timePassed / 1000_000_000.0);
  }

  public static long getTimeInMiliSeconds(long timePassed) {
    return (long) (timePassed / 1000_000.0);
  }

  public static double mapRandomValue(double randomValue) {
    return 0 + ((800)) * (randomValue - 0);
  }

  public static double mapRange(double input, double inputStart, double inputEnd,
      double outputStart,
      double outputEnd) {
    return outputStart + ((outputEnd - outputStart) / (inputEnd - inputStart)) * (input
        - inputStart);
  }

  public static Optional<List<String>> readNames() {

    ClassLoader classLoader = GameUtility.class.getClassLoader();

    try(Scanner readNames = new Scanner(
        new File(classLoader.getResource("AwesomeNames.txt").getFile()));) {

      List<String> names = new ArrayList<>();
      while (readNames.hasNextLine()) {
        names.add(readNames.nextLine());
      }
      return Optional.of(names);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return Optional.empty();
  }
}
